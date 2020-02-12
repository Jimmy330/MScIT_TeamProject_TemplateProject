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
                background-image: url(https://i.ibb.co/rmBjPS4/background.jpg);
                background-color: rgba(0, 0, 0, 0.2);
                background-size: cover;
            }

            button {
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
			dialog{
				display: none;
			}
        </style>

	</head>

    <body> 
    	
    	<div class="container">

            <!-- Add your HTML Here -->
            <div id="Banner" style="text-align: center;">
                <H1 style="background-color: rgba(0, 0, 0, 0.7);font-size: 5rem;font-weight: bold;color: aliceblue;font-style: italic;">Toptrumps</H1>
            </div>
			<div id="ButtonNewGame" style="text-align: center;margin-top: 10rem;">
				<!-- "window.location.href='game'" -->
                <button onclick=showDialog()>START NEW GAME</button>
				<button onclick="window.location.href='stats'">GAME STATISTICS</button>
				<dialog id="dialog">
					<h6 id="close" style="text-align: right;" onclick=closeDialog()>X</h6>
					<h5>Please choose the number of players you want to play against.</h5>
					<select id="select" style="width: 100px;margin-top: 30px;" >
						<option value="4">4</option>
						<option value="3">3</option>
						<option value="2">2</option>
						<option value="1">1</option>	
					</select>
					Player
					<div>
						<button id="play" onclick=startGame() style="width: 150px;font-size: smaller;margin-top: 20px;">PLAY NOW</button>
					</div>
					
				</dialog>

				
            
            </div>
			
		
		</div>
		
		<script type="text/javascript">
		

			function closeDialog(){
				document.getElementById("dialog").style.display="none";

			}

			function showDialog(){
				document.getElementById("dialog").style.display="block";
			}
			
			function startGame(){
				var x=document.getElementById("select");
				
				return window.location.href='game'


			}
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
            function selectPlayer(){
                //var numOfPlayer=prompt("Enter the number of players you want to play against.",4)
                
            
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
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>