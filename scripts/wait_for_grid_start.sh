#!/usr/bin/env bash

# Configurable Selenium status endpoint (falls back to localhost)
STATUS_URL="${SELENIUM_STATUS_URL:-http://localhost:4444/status}"

# Bring up the Grid
docker compose -f docker-compose.yml up -d

echo "Waiting for Selenium Grid at $STATUS_URL …"
# Allow curl failures without exiting the script
set +e

for i in {1..10}; do
  # Try fetching status (capture both stdout and stderr)
  resp=$(curl -s "$STATUS_URL" 2>&1)
  code=$?
  echo "Attempt $i — curl exit code: $code"
  echo "Attempt $i — response: $resp"

  # Only consider "ready:true" if curl itself succeeded
  if [ $code -eq 0 ] && echo "$resp" | grep -q '"ready": true'; then
    echo "✅ Grid is ready"
    # Re-enable exit-on-error and exit successfully
    set -e
    exit 0
  fi

  echo "  ↳ not ready yet, retrying in 5s…"
  sleep 5
done

# Final failure case: show last captured output and error code
echo "❌ Grid failed to start. Last curl exit code: $code"
echo "❌ Last response was:"
echo "$resp"
exit 1