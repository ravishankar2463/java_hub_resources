param(
  [int]$StartLevel = 0,
  [int]$EndLevel = 15,
  [string]$Model = "qwen2.5:7b",
  [int]$Limit = 0,
  [switch]$Minimized
)

$studioDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$logFile = Join-Path $studioDir "batch-all.log"

$nodeCmd = Get-Command node -ErrorAction SilentlyContinue
$nodeDir = if ($nodeCmd) { Split-Path -Parent $nodeCmd.Source } else { "" }
$ollamaDir = Join-Path $env:LOCALAPPDATA "Programs\Ollama"
$ollamaExe = Join-Path $ollamaDir "ollama.exe"

$pathPrepend = @($nodeDir, $ollamaDir) | Where-Object { $_ -and (Test-Path $_) }
$pathInit = if ($pathPrepend.Count -gt 0) {
  "`$env:PATH = '$($pathPrepend -join ';');' + `$env:PATH; "
} else { "" }

# Ensure Ollama server is started inside the spawned window so it persists independently
$ollamaBoot = "try { Invoke-RestMethod http://127.0.0.1:11434/api/tags -TimeoutSec 2 | Out-Null } catch { if (Test-Path '$ollamaExe') { Start-Process '$ollamaExe' -ArgumentList 'serve' -WindowStyle Hidden; Start-Sleep -Seconds 3 } }; "

$argsList = @("run", "batch-all", "--", "--start-level", "$StartLevel", "--end-level", "$EndLevel", "--model", "$Model")
if ($Limit -gt 0) {
  $argsList += @("--limit", "$Limit")
}

Write-Host "Starting JavaHub zero-credit local batch runner in a new PowerShell window..." -ForegroundColor Green
Write-Host "  Range : Level $StartLevel .. Level $EndLevel"
Write-Host "  Model : $Model (local Ollama on RTX 2060)"
Write-Host "  Log   : $logFile"

$cmd = "$pathInit $ollamaBoot Set-Location '$studioDir'; npm $($argsList -join ' ') *>&1 | Tee-Object -FilePath '$logFile' -Append"
$winStyle = if ($Minimized) { "Minimized" } else { "Normal" }
Start-Process powershell -ArgumentList "-NoExit", "-Command", $cmd -WindowStyle $winStyle

Write-Host "`nLaunched new PowerShell terminal! You can also tail the log anytime with:" -ForegroundColor Cyan
Write-Host "  Get-Content '$logFile' -Wait -Tail 30"
