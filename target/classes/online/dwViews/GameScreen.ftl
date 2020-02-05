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
    <link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css" />
    <link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css" />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

    <!--Define our own Styling of the Website-->
    <style>
        body {
            background-image: url(https://i.ibb.co/rmBjPS4/background.jpg);
            background-size: cover;
        }

        .div1 {
            margin-top: 50px;
        }

        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            transition: 0.3s;
            width: 180px;
            border-radius: 5px;
            border-color: rgba(0, 0, 0, 0.2);
            float: left;
            margin-right: 5%;
            text-align: center;
            background-color: rgba(243, 247, 247, 0.644);
        }

        .card:hover {
            box-shadow: 0 80px 16px 0 rgba(0, 0, 0, 0.0.2);
        }

        img {
            border-radius: 5px 5px 0 0;
            border-color: rgba(0, 0, 0, 0.2);
        }

        td {
            padding-left: 1rem;
            border-top: 1px dashed rgba(0, 0, 0, 0.2);
            border-bottom: 1px dashed rgba(0, 0, 0, 0.2);


        }

        p {
            margin-top: 0.5rem;
            margin-bottom: 0.5rem;
            font-size: 0.8rem;
        }

        button {
            background-color: rgba(0, 0, 0, 0.6);
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 12px;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>

</head>

<body onload="initalize()">
    <!-- Call the initalize method when the page loads -->

    <div class="container">

        <!-- Add your HTML Here -->
        
        <div>
            <H1 style="font-size: 5rem;font-weight: bold;color: aliceblue;font-style: italic;">Toptrumps</H1>
        </div>
        <div style="widows: inherit;;height: 350px;background-color: khaki;">
            <div id="humanCard" class="card">
                <h5 id="humanPlayerName"></h5> 
                <h5 id="numOfCard_human"></h5>
                <img src="https://i.ibb.co/rmBjPS4/background.jpg" alt="" style="width: 100%;"></img>
                <h5 id="cardName_human" ></h5>
                <table id="humanPlayer"> </table>
            </div>
            <div id="menu" style="width: 360px;float: left;height: 350px;background-color: rgb(240, 200, 140);">
                <div id="round" style="width: 360px;height: 30px;background-color: lavenderblush;">
                    <h1 id="roundNo"></h1>
                </div>
                <div id="messageArea" style="width: inherit;height: 90px;background-color: lightcoral;">
                    <p id="message"></p>

                </div>
                <div id="button" style="width: inherit;height: 30px;background-color: lawngreen;">
                    <button id="showWinner" onclick="roundWinner()">Show Winner</button>

                </div>

            </div>
        </div>

        <div id="AIcardArea" style="widows: inherit;height: 350px;">
            <div id="AI1" class="card">
                <h5 id="AI1_Name">Jam (7)</h5> 
                <strong id="numOfCard_AI1"></strong>
                <img src="https://i.ibb.co/rmBjPS4/background.jpg" alt="" style="width: 100%;"></img>
                <h5 id="cardName_AI1" ></h5>
                <table id="AIPlayer1"></table>
            </div>

            <div id="AI2" class="card">
                <h5 id="AI2_Name">Jam (7)</h5> 
                <strong id="numOfCard_AI2"></strong>
                <img src="https://i.ibb.co/rmBjPS4/background.jpg" alt="" style="width: 100%;">
                <h5 id="cardName_AI2" ></h5>
                <table id="AIPlayer2"></table>
            </div>

            <div id="AI3" class="card">
                <h5 id="AI3_Name">Jam (7)</h5> 
                <strong id="numOfCard_AI3"></strong>
                <img src="https://i.ibb.co/rmBjPS4/background.jpg" alt="" style="width: 100%;">
                <h5 id="cardName_AI3" ></h5>
                <table id="AIPlayer3"></table>
            </div>

            <div id="AI4" class="card">
                <h5 id="AI4_Name">Jam (7)</h5> 
                <strong id="numOfCard_AI4"></strong>
                <img src="https://i.ibb.co/rmBjPS4/background.jpg" alt="" style="width: 100%;">
                <h5 id="cardName_AI4" ></h5>
                <table id="AIPlayer4"></table>
            </div>

        </div>


        <div>
            <h5 style="text-align: center;">copyright@www.f5.com</h5>

        </div>
    </div>


    </div>

    <script type="text/javascript">

        // Method that is called on page load
        function initalize() {

            // --------------------------------------------------------------------------
            // You can call other methods you want to run when the page first loads here
            // --------------------------------------------------------------------------

            // For example, lets call our sample methods
            
            loadCards(); 
            getRoundNo();
            getSelector();

        }

        // -----------------------------------------
        // Add your other Javascript methods Here
        // -----------------------------------------

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
            xhr.onload = function (e) {
                var responseText = xhr.response; // the text of the response
                
                alert(responseText); // lets produce an alert
            };

            // We have done everything we need to prepare the CORS request, so send it
            xhr.send();
        }

        // This calls the helloJSONList REST method from TopTrumpsRESTAPI
        function helloWord(word) {

            // First create a CORS request, this is the message we are going to send (a get request in this case)
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word=" + word); // Request type and URL+parameters

            // Message is not sent yet, but we can check that the browser supports CORS
            if (!xhr) {
                alert("CORS not supported");
            }

            // CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
            // to do when the response arrives 
            xhr.onload = function (e) {
                var responseText = xhr.response; // the text of the response
                alert(responseText); // lets produce an alert
            };

            // We have done everything we need to prepare the CORS request, so send it
            xhr.send();
        }

        function getRoundNo(){
            var xhr =createCORSRequest('GET', "http://localhost:7777/toptrumps/roundNo");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var responseText = xhr.response; 
                document.getElementById("roundNo").innerHTML="Round "+responseText;
                //alert(responseText); // lets produce an alert
            };

            xhr.send();
        }
        


        function loadCards(){
            var xhr = createCORSRequest('GET',"http://localhost:7777/toptrumps/card");
            if(!xhr){
                alert("CORS not supported");
            }
            xhr.onload = function(e){
                var responseText = xhr.response; 
                var card=JSON.parse(responseText);
                //alert(card);

                // human card
                document.getElementById("humanPlayerName").innerHTML=card[0][0];
                document.getElementById("numOfCard_human").innerHTML=card[0][1];
                document.getElementById("cardName_human").innerText=card[0][2];
                var category ="";               
                for(var i=3;i<8;i++ ) {
                    category +="<tr><td>"+card[0][i]+"</td></tr>"
                };
                document.getElementById("humanPlayer").innerHTML=category;

                // AI1 card
                document.getElementById("AI1_Name").innerHTML=card[1][0];
                document.getElementById("numOfCard_AI1").innerHTML=card[1][1];
                document.getElementById("cardName_AI1").innerText=card[1][2];
                var category ="";               
                for(var i=3;i<8;i++ ) {
                    category +="<tr><td>"+card[1][i]+"</td></tr>"
                };
                document.getElementById("AIPlayer1").innerHTML=category;

                // AI2 card
                document.getElementById("AI2_Name").innerHTML=card[2][0];
                document.getElementById("numOfCard_AI2").innerHTML=card[2][1];
                document.getElementById("cardName_AI2").innerText=card[2][2];
                var category ="";               
                for(var i=3;i<8;i++ ) {
                    category +="<tr><td>"+card[2][i]+"</td></tr>"
                };
                document.getElementById("AIPlayer2").innerHTML=category;

                // AI3 card
                document.getElementById("AI3_Name").innerHTML=card[3][0];
                document.getElementById("numOfCard_AI3").innerHTML=card[3][1];
                document.getElementById("cardName_AI3").innerText=card[3][2];
                var category ="";               
                for(var i=3;i<8;i++ ) {
                    category +="<tr><td>"+card[3][i]+"</td></tr>"
                };
                document.getElementById("AIPlayer3").innerHTML=category;

                // AI4 card
                document.getElementById("AI4_Name").innerHTML=card[4][0];
                document.getElementById("numOfCard_AI4").innerHTML=card[4][1];
                document.getElementById("cardName_AI4").innerText=card[4][2];
                var category ="";               
                for(var i=3;i<8;i++ ) {
                    category +="<tr><td>"+card[4][i]+"</td></tr>"
                };
                document.getElementById("AIPlayer4").innerHTML=category;

    
            }
            xhr.send();


        }

        function getSelector(){
            var xhr =createCORSRequest('GET', "http://localhost:7777/toptrumps/select");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var responseText =JSON.parse(xhr.response) ; 
                
                alert(responseText);
                if(responseText[0]=="You"){
                    document.getElementById("message").innerHTML="It's your turn to select a category!"
                }
                document.getElementById("message").innerHTML=responseText[0]+" selected "+responseText[1];
                
            };

            xhr.send();
        }

        function roundWinner(){
            var xhr =createCORSRequest('GET', "http://localhost:7777/toptrumps/roundWinner");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var responseText = xhr.response; 
                document.getElementById("message").innerHTML=responseText+" win this round ";
           //add css show AI cards

            xhr.send();
        }
        }

        // function nextRound(){
        //     var xhr =createCORSRequest('GET', "http://localhost:7777/toptrumps/nextRound");
        //     if (!xhr) {
        //         alert("CORS not supported");
        //     }
        //     xhr.onload = function (e) {
        //         var responseText = xhr.response; 
        //         document.getElementById("message").innerHTML=responseText+" win this round ";
        //    //add css show AI cards

            
        // }



    </script>

</body>

</html>