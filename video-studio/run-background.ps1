param(
  [int]$StartLevel = 0,
  [int]$EndLevel = 15,
  [string]$Model = "qwen2.5:7b",
  [int]$Limit = 0
)

$studioDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$logFile = Join-Path $studioDir "batch-all.log"

$argsList = @("run", "batch-all", "--", "--start-level", "$StartLevel", "--end-level", "$EndLevel", "--model", "$Model")
if ($Limit -gt 0) {
  $argsList += @("--limit", "$Limit")
}

Write-Host "Starting JavaHub zero-credit local batch runner in background..." -ForegroundColor Green
Write-Host "  Range : Level $StartLevel .. Level $EndLevel"
Write-Host "  Model : $Model (local Ollama on RTX 2060)"
Write-Host "  Log   : $logFile"

$cmd = "Set-Location '$studioDir'; npm $($argsList -join ' ') *>&1 | Tee-Object -FilePath '$logFile' -Append"
Start-Process powershell -ArgumentList "-NoExit", "-Command", $cmd -WindowStyle Minimized

Write-Host "`nLaunched minimized background PowerShell window! Watch live progress with:" -ForegroundColor Cyan
Write-Host "  Get-Content '$logFile' -Wait -Tail 30"
