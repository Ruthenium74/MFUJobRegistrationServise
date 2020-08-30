#Сервис учёта использования печатных устройств и предоставления статистики
Полное описание см. task_for_developers.pdf
#### Отправить информацию об использовании печатных устройств
`curl -s -X POST -d @jobs.xml -H 'Content-Type:application/xml;charset=UTF-8' http://localhost:8080/MfuJobRegistrationService/jobs`

#### Запросить статистику об использовании печатных устройств
`curl -s http://localhost:8080/MfuJobRegistrationService/statistics?user=user1&timeFrom=2020-08-30T20:00:00`