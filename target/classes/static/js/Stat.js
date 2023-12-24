var chartData = {
    labels: ['Clients', 'Maintenancier', 'Mars', 'Avril', 'Mai', 'Juin'],
    datasets: [{
      label: 'Ventes',
      data: [12, 19, 3, 5, 2, 3],
      backgroundColor: 'rgba(54, 162, 235, 0.5)',
      borderColor: 'rgba(54, 162, 235, 1)',
      borderWidth: 1
    }]
  };

  // Options du diagramme
  var chartOptions = {
    scales: {
      y: {
        beginAtZero: true
      }
    }
  };

  // Créer le diagramme
  var ctx = document.getElementById('myChart').getContext('2d');
  var myChart = new Chart(ctx, {
    type: 'bar',
    data: chartData,
    options: chartOptions
  });

    // Données du diagramme
    var chartData = {
    labels: ['Rouge', 'Vert', 'Bleu'],
    datasets: [{
      data: [40, 30, 20],
      backgroundColor: ['rgba(255, 99, 132, 0.5)', 'rgba(75, 192, 192, 0.5)', 'rgba(54, 162, 235, 0.5)'],
      borderColor: ['rgba(255, 99, 132, 1)', 'rgba(75, 192, 192, 1)', 'rgba(54, 162, 235, 1)'],
      borderWidth: 1
    }]
  };

  // Options du diagramme
  var chartOptions = {};

  // Créer le diagramme
  var ctx = document.getElementById('msChart').getContext('2d');
  var myChart = new Chart(ctx, {
    type: 'pie',
    data: chartData,
    options: chartOptions
  });
