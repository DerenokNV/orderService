# orderService
Приложение будет отправлять сообщения в очередь Kafka, а так же, слушать другие топики Kafka и при наличии сообщений, выводить данные в лог.

web+kafka+docker  

1. Запустить docker-start.cmd, лежит в orderService  
2. Запустить сервсис order-service   
   - обрабатывает POST-запрос с сущностью Order  
   - отправляет событие в Kafka - OrderEvent, в топик "order-topic"  
   - слушает топик "order-status-topic"  
3. Запустить сервис order-status-service   
   - слушает топик "order-topic"  
   - при наличии события в топике "order-topic", отправляет событие в топик "order-status-topic"  
