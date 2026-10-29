# Fast topic switcher for PowerShell
param(
  [Parameter(Mandatory=$false, Position=0)]
  [string]$Level,
  [Parameter(Mandatory=$false, Position=1)]
  [string]$Slug
)

if (-not $Level -or -not $Slug) {
  Write-Host "Usage: .\switch-topic.ps1 <level-number> <topic-slug>" -ForegroundColor Yellow
  Write-Host "Example: .\switch-topic.ps1 8 spring-core-internals" -ForegroundColor Cyan
  Write-Host "Or: git checkout topic/level-8/spring-core-internals" -ForegroundColor Green
  exit
}

$Branch = "topic/level-$Level/$Slug"
Write-Host "Switching to branch: $Branch..." -ForegroundColor Cyan
git checkout $Branch
