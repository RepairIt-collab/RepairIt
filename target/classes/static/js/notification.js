var stompClient = null;

        function connect() {
            var socket = new SockJS('/websocket');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
            });
            
        }

        function disconnect() {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            console.log("Disconnected");
        }

        function sendNotification(userId,message) {
            var destination = '/app/notification/' + userId;
            // var message = 'Notification pour l\'utilisateur ' + userId;

            stompClient.send(destination, {}, message);
        }

        function sendNotificationN(notification) {
            Notification.requestPermission()
            // Vérifier si le navigateur prend en charge les notifications
            if ('Notification' in window && Notification.permission === 'granted') {
            // Créer une nouvelle notification
            var options = {
                body: notification,
                icon: 'public/images/Outil.png' // Spécifiez le chemin vers l'icône de la notification
            };
            var notification = new Notification('Nouvelle taches', options);
        
        
            // Gérer les événements de la notification (clic, fermeture, etc.)
            notification.onclick = function(event) {
                // Gérer le clic sur la notification
            };
        
            notification.onclose = function(event) {
                // Gérer la fermeture de la notification
            };
        
            // ...
            }
        }

        connect();

    