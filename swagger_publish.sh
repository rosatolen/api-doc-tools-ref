#!/usr/bin/env bash
#
# for publishing an OpenAPI spec to Swaggerhub. It's dead simple, you just need 3 variables
# 1. SwaggerHub API KEY
# 2. Owner of the API
# 3. API name
#
######

curl -H 'Authorization: $SWAGGERHUB_API_KEY' -H 'Content-Type: application/json' -H 'Accept: application/json' \
    -X POST -d @spec.json 'https://api.swaggerhub.com/apis/exampleOwner/myApi?isPrivate=true'
