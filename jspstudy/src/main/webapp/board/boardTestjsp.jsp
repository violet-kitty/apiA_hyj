<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
</head>
<body>
<canvas id="pie-chart" width="500" height="500"></canvas>
<script>
new Chart(document.getElementById("pie-chart"), {
    type: 'line',
    data: {
      labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
      datasets: [{
        label: "label1",
        backgroundColor: ["none"],
        data: [100,200,734,784,433]
      },
      {
            label: "label2",
            backgroundColor: ["#ff5555"],
            data: [200,200,100,50,800]
      }]
    },
    options: {
    	responsive : false,
      title: {
        display: true,
        text: 'title'
      }
    }
});
</script>
</body>
</html>