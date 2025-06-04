#!/usr/bin/env bash

APP_PORT="${THE_INTERNET_PORT:-7080}"
APP_URL="http://localhost:$APP_PORT"

docker pull gprestes/the-internet:v2.6.5

docker run -d -p "$APP_PORT":5000 gprestes/the-internet:v2.6.5

echo "Waiting for The Internet to be started at $APP_URL …"
# Allow curl failures without exiting the script
set +e

for i in {1..10}; do
  # Try fetching status (capture both stdout and stderr)
  status=$(curl -s -o /dev/null -w "%{http_code}" "$APP_URL" 2>&1)
  code=$?
  echo "Attempt $i — curl exit code: $code"
  echo "Attempt $i — response status: $status"

  # Only consider "ready:true" if curl itself succeeded
  if [ $code -eq 0 ] && echo "$status" | grep -q '200'; then
    echo "✅ The Internet is up!"
    # Re-enable exit-on-error and exit successfully
    set -e
    exit 0
  fi

  echo "  ↳ not ready yet, retrying in 5s…"
  sleep 5
done

# Final failure case: show last captured output and error code
echo "❌ The Internet failed to start. Last curl exit code: $code"
echo "❌ Last response status was:"
echo "$status"
exit 1