#!/usr/bin/env bash
curl -X POST --data-binary @req.json -H 'Content-Type: application/json' http://127.0.0.1:8080/api/checkout
echo
