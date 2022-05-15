# spring-boot-demo
Spring boot demo project

**Important:**

Insomnia and Postman don't support multi-part/mixed requests, but curl does

```
curl -i -X PUT 'http://localhost:8080/batches/abc' \
-H 'Content-Type:multipart/mixed' \
-H 'X-TENANT-ID:1234' \
-F 'profile={"id":1234,"name":"Test"};type=application/json' \
-F 'file=@/home/devaprasadh/Downloads/messages.txt;type=text/plain'
```