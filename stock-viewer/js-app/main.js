document.getElementById('stock_input-form').addEventListener('submit', getStockPriceDetails);

function fetchDetails() {
  var stockDetails = document.getElementById('stock_details');

  stockDetails.innerHTML = '<p class="text-info">' + 'No data available.' + '</p>';
}

function getStockPriceDetails(e) {
  var userName = document.getElementById('user_name').value;
  var stockDetails = document.getElementById('stock_details');
  
  const url = "http://localhost:7002/api/db-service/data/" + "rcb";
  //const url = 'https://randomuser.me/api/?results=2';
  var result = "";

  fetch(url, {mode: 'no-cors'})
    //.then((response) => response.json())
    .then(function(response) {
      console.log('Request successful. Response: ', response);
    })
    .catch(function(error) {
      console.log('Request failed. Error: ', error)
    });  

  if (userName == undefined || userName == "") {
    stockDetails.innerHTML = '<p class="text-danger">' + 'You entered empty user name.' + '</p>';
  } else {
    stockDetails.innerHTML = '<p class="text-success">' + 'Text: ' + result + '</p>';
  }

  document.getElementById('stock_input-form').reset();
  e.preventDefault();                        
}
