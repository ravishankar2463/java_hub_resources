#!/usr/bin/env bash
# Fast topic switcher for JavaHub Resources
if [ -z "$1" ]; then
  echo "Usage: ./switch-topic.sh <level-number> <topic-slug>"
  echo "Example: ./switch-topic.sh 8 spring-core-internals"
  echo "Or: git checkout topic/level-8/spring-core-internals"
  exit 1
fi

LEVEL="level-$1"
SLUG="$2"
BRANCH="topic/$LEVEL/$SLUG"

echo "Switching to branch: $BRANCH..."
git checkout "$BRANCH"
