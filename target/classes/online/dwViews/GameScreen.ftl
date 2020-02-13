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
            background-image: url(/assets/background.jpeg);
            background-size: cover;
        }

        .cardarea {
            width: 170px;
            height: 300px;          
            float: left;
            display: block;
            margin-left: 4%;

        }

        .card {

            padding: 10px;
            width: 170px;
            height: 260px;
            border-radius: 5px;
            text-align: center;
            background-color: rgba(255, 255, 255);
            display: none;
            

        }

        .cardback {

            width: 170px;
            height: 260px;
            border-radius: 5px;
            text-align: center;
            display: block;

        }
        .losebackground{
            
            width: 170px;
            height: 260px;

            border-radius: 5px;
            text-align: center;
            padding: 170px 0;
            background-color: rgb(10, 10, 10);
        }


        img {
            border-radius: 5px 5px 0 0;
            border-color: rgba(0, 0, 0, 0.2);
            width: 100%;
        }

        td {

            border-top: 1px dashed rgba(0, 0, 0, 0.2);
            border-bottom: 1px dashed rgba(202, 199, 199, 0.2);
            width: 100%;
        }

        button {
            background-color: rgba(202, 199, 199, 0.2);
            border: none;
            width: 100%;
            height: 50px;
            color: rgb(255, 255, 255);
            margin-top: 10px;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            display: none;
        }

        .dropdown {
            width: 100%;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: rgba(0, 0, 0, 0.603);
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            width: inherit;
        }

        .dropdown-content a {
            color: rgb(252, 249, 249);
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {
            border: 2px solid rgba(248, 245, 245, 0.712);
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .numOfCard {
            text-align: right;
            color: azure;
        }

        .playerName {
            float: left;
            color: azure;
        }

        a {
            text-align: center;
        }
    </style>

</head>

<body onload="initalize()">
    <!-- Call the initalize method when the page loads -->

    <div class="container">

        <!-- Add your HTML Here -->

        <div>
            <h1 style="font-weight: bold;color:rgba(255, 255, 255, 0.664);font-style: italic;text-align: right;">
                Toptrumps</h1>
        </div>

        <div id="menu" style="width: 22%;height: 640px;background-color: rgba(0, 0, 0, 0.8);float: left;">
            <div id="round" style="height: 5rem;border-style:groove;border-width: 1px;padding-left: 5px;">
                <h2 id="roundNo" style="color: aliceblue;"></h2>
                <h5 id="commonDeck" style="color: aliceblue;"></h5>
            </div>
            <div id="message"
                style="height: 200px;font-size: medium;color:aliceblue;;background-color:rgba(128, 127, 127, 0.199);padding: 0.8rem;">
            </div>
            <div id="button">
                <button id="showWinner" onclick="roundWinner()">SHOW WINNER</button>
                <button id="nextRound" onclick="nextRound()"> NEXT ROUND</button>
                <button id="return" onclick="window.location.href='http://localhost:7777/toptrumps/'">RETURN TO SELECT SCREEN</button>
                <div id="category" class="dropdown">
                    <button id="select">SELECT CATEGORY</button>
                    <div class="dropdown-content">
                        <a href="#" onclick=selectCategory(1)>HP</a>
                        <a href="#" onclick=selectCategory(2)>Physical</a>
                        <a href="#" onclick=selectCategory(3)>Defense</a>
                        <a href="#" onclick=selectCategory(4)>Magic</a>
                        <a href="#" onclick=selectCategory(5)>Speed</a>
                    </div> 
                </div>

            </div>

        </div>
        <div id="humanCardArea" style="width: 78%;height: 320px;;background-color:rgba(0, 0, 0, 0.8);float: left;">
            <div id="You" class="cardarea" style="display: block">
                <h3 id="humanPlayerName" class="playerName"></h3>
                <h5 id="numOfCard_human" class="numOfCard"></h5>
                <div id="cardYou" class="card" style="display: block;">
                    <img id="imgs" alt="Opps~!picture is missing"></img>
                    <h5 id="cardName_human"></h5>
                    <table id="humanPlayer"> </table>
                </div>
            </div>

        </div>

        <div id="AIcardArea" style="width: 78%;height: 320px;background-color: rgba(0, 0, 0, 0.8); float: left;">
        </div>


    </div>


    </div>

    <script type="text/javascript">

        // Method that is called on page load
        function initalize() {

            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/initalize");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var responseText = JSON.parse(xhr.response);
                console.log(responseText);

                loadCards();
                getRoundNo();
                getCommonDeck();
                getSelector();
            }
            xhr.send();


        }

        // -----------------------------------------
        // Add your other Javascript methods Here
        // -----------------------------------------
        function getRoundNo() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/roundNo");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var responseText = xhr.response;
                document.getElementById("roundNo").innerHTML = "Round " + responseText;
            };

            xhr.send();
        }

        function getCommonDeck() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/commonDeck");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var responseText = xhr.response;
                document.getElementById("commonDeck").innerHTML = "Communal Pile (" + responseText + ")";
            };

            xhr.send();

        }


        var playerCard = "";

        function loadCards() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/card");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                playerCard = JSON.parse(xhr.response);
                console.log(playerCard);

                // load human card deck
                document.getElementById("humanPlayerName").innerHTML = playerCard[0].name;
                document.getElementById("numOfCard_human").innerHTML = "Card(" + playerCard[0].numOfCards + ")";
                document.getElementById("cardName_human").innerText = playerCard[0].hand.name;
                document.getElementById("imgs").src=loadImg(playerCard[0].hand.name);
                var categoryName = ["HP","Physical","Defense","Magic","Speed"];
                var category = "";
                for (var i = 0; i < categoryName.length; i++) {
                    category += "<tr><td>" + categoryName[i] + "</td><td>" + playerCard[0].hand.category[i + 1] + "</td></tr>"
                };
                document.getElementById("humanPlayer").innerHTML = category;
                document.getElementById("cardYou").style.backgroundColor = "rgb(255, 255, 255)";

                // load AIs card deck here
                for (var i = 1; i < playerCard.length; i++) {

                    var ai_cardarea = document.createElement("div");
                    ai_cardarea.className = "cardarea";
                    ai_cardarea.id = playerCard[i].name;

                    var playername = document.createElement("h5");
                    playername.innerText = playerCard[i].name;
                    playername.className = "playerName";
                    ai_cardarea.appendChild(playername);

                    var numOfCards = document.createElement("h6");
                    numOfCards.className = "numOfCard";
                    numOfCards.innerText = "Card(" + playerCard[i].numOfCards + ")"

                    ai_cardarea.appendChild(numOfCards);

                    document.getElementById("AIcardArea").appendChild(ai_cardarea);

                    var card = document.createElement("div");
                    card.className = "card";
                    card.id = "card" + playerCard[i].name;

                    ai_cardarea.appendChild(card);

                    var cardName = document.createElement("h5");
                    cardName.innerText = playerCard[i].hand.name;

                    var img = document.createElement("img");
                    img.src=loadImg(playerCard[i].hand.name);

                    card.appendChild(img);            

                    card.appendChild(cardName);

                    var categoryInfo = document.createElement("table");

                    var category = "";
                    for (var j = 0; j < categoryName.length; j++) {
                        category += "<tr><td>" + categoryName[j] + "</td><td>" + playerCard[i].hand.category[j + 1] + "</td></tr>"
                    };
                    categoryInfo.innerHTML = category;

                    card.appendChild(categoryInfo);

                    var cardback = document.createElement("img");
                    cardback.src = "/assets/cardback.png";
                    cardback.className = "cardback";
                    cardback.id = "cardback" + playerCard[i].name;

                    ai_cardarea.appendChild(cardback);

                }

                for (var i = 0; i < playerCard.length; i++) {
                    if (playerCard[i].alive == false) {
                        document.getElementById(playerCard[i].name).style.display = "none";
                    }
                }
            }

            xhr.send();

        }

        // load images method based on card name chosing relative picture
        function loadImg(imgs){
            var imgURL;
            switch(imgs){
                case "Dreepy": imgURL="/assets/dreepy.png" ;break;
                case "Applin": imgURL="/assets/applin.png" ;break;
                case "Eiscue": imgURL="/assets/eiscue.png" ;break;
                case "Gossifleur": imgURL="/assets/gossifleur.png" ;break;
                case "Grookey": imgURL="/assets/grookey.png" ;break;
                case "Milcery": imgURL="/assets/milcery.png" ;break;
                case "Morpeko": imgURL="/assets/morpeko.png" ;break;
                case "Scorbunny": imgURL="/assets/scorbunny.png" ;break;
                case "Sinistea": imgURL="/assets/sinistea.png" ;break;
                case "Sobble": imgURL="/assets/sobble.png" ;break;
                case "Wooloo": imgURL="/assets/wooloo.png" ;break;
                case "Yamper": imgURL="/assets/yamper.png" ;break;

            }
            return imgURL;

        }

        // get selector for each round
        function getSelector() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/selector");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var selector = JSON.parse(xhr.response);
                console.log(selector);

                if (selector.type == 1) {
                    document.getElementById("message").innerHTML = "It's your turn to select a category!"
                    document.getElementById("select").style.display = "block";
                    document.getElementById("category").style.display="block";
                } else {
                    document.getElementById("message").innerHTML = "The active player: "+selector.name + "<br> Selected <strong>" + selector.category+"</strong>";
                    document.getElementById("showWinner").style.display = "block";
                }

            }

            xhr.send();
        }

        function selectCategory(category) {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/category?value=" + category);
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var selectResult = JSON.parse(xhr.response);
                console.log(selectResult);
                document.getElementById("showWinner").style.display = "block";
                document.getElementById("category").style.display="none";
                document.getElementById("message").innerHTML = selectResult.name + " selected <strong>" + selectResult.category+"</strong>";

            };

            xhr.send();
        }

        function roundWinner() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/roundWinner");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var roundWinner = JSON.parse(xhr.response);
                console.log(roundWinner);

                for (var i = 1; i < playerCard.length; i++) {
                    document.getElementById("card" + playerCard[i].name).style.display = "block";
                    document.getElementById("cardback" + playerCard[i].name).style.display = "none";
                };
                if (roundWinner[0] == 0) {
                    var message = "Opps~! This round is a draw! <br>" + "There are " + roundWinner[1] + " cards in Communal Pile now"
                    document.getElementById("message").innerHTML = message;
                } else {
                    document.getElementById("message").innerHTML = roundWinner.name + " win this round ";
                    document.getElementById("card" + roundWinner.name).style.backgroundColor = "darkseagreen";

                }
                document.getElementById("showWinner").style.display = "none";
                document.getElementById("nextRound").style.display = "block";

            }
            xhr.send();
        }

        function nextRound() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/newRound");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var nextRound = JSON.parse(xhr.response);
                console.log(nextRound);
                document.getElementById("nextRound").style.display = "none";
                if (nextRound == false) {
                    gameOver();
                } else {
                    for (var i = 1; i < playerCard.length; i++) {
                        document.getElementById("AIcardArea").removeChild(document.getElementById(playerCard[i].name))
                    };

                    getCommonDeck();
                    loadCards();
                    getRoundNo();
                    getSelector();
                }


            }
            xhr.send();

        }

        function gameOver() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/gameWinner");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var gameOver = JSON.parse(xhr.response);
                console.log(gameOver);
                var message = "Game Over! <br>Game Winner is <strong>" + gameOver[0].gameWinner + "</strong><br>";
                var gamedata = "";
                for (var key in gameOver[1]) {
                    gamedata += key + " won " + gameOver[1][key] + " rounds <br>";

                }

                document.getElementById("message").innerHTML = message + gamedata;
                document.getElementById("showWinner").style.display = "none";
                document.getElementById("return").style.display = "block";
                //document.getElementById(gameOver[0].gameWinner).style.display = "block";

            }
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