docker compose up -d
docker compose down

grep -rnw '/Applications/Docker.app/Contents/Resources' -e 'defaultKeepStorage'