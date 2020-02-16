<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

		 <style>
            body {
                background-image: url(/assets/background.jpg);
                background-color: rgba(0, 0, 0, 0.2);
                background-size: cover;
            }

            button{
                background-color: rgba(0, 0, 0, 0.9);
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 20px;
                cursor: pointer;
				width: 300px;
            }

            table{
                background-color: rgba(0, 0, 0, 0.9);
                width: 700px;
                border-style: groove;
                border-color:antiquewhite;
                color: white;
                padding: 15px 32px;
                text-align: left;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 20px;
                cursor: pointer;

            }
        </style>

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">

		<!-- Add your HTML Here -->
		<div id="Banner" style="text-align: center;">
            <H1 style="background-color: rgba(0, 0, 0, 0.7);font-size: 5rem;font-weight: bold;color: aliceblue;font-style: italic;">Toptrumps</H1>
        </div>

        <!-- Game data table -->
		<div style="text-align: center;margin-top: 6rem;">
            <table style="border-collapse:separate; border-spacing:20px 15px;">
                <tr>
					<td>Number of Games: </td>
					<td id="d0"></td>
                </tr>

                <tr>
					<td>Number of Human Wins: </td>
					<td id="d1"></td>
                </tr>

                <tr>
					<td>Number of AI Wins: </td>
					<td id="d2"></td>
                </tr>

                <tr>
					<td>Average Draws per game: </td>
					<td id="d3"></td>
                </tr>
                
                <tr>
					<td>Longest Game: </td>
					<td id="d4"></td>
                </tr>
            </table>
		</div>

		<!-- Return button -->
		<div id="buttons" style="text-align: center; margin-top: 2rem;">
			<button onclick="window.location.href='http://localhost:7777/toptrumps/'">RETURN TO SELECT SCREEN</button>
        </div>
		
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/gameData"); 
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var gameData = JSON.parse(xhr.response);
					// Get the game data from database
					for(var i = 0; i<gameData.length;i++){
						document.getElementById("d"+i).innerHTML=gameData[i];
					}	
				
				};

				xhr.send();	
			
				
			}
			
		
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		
		</body>
</html>