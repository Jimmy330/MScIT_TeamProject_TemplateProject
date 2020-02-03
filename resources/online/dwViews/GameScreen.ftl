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
                <h5 id="caren">Jam (7)</h5> 
                <img src="https://i.ibb.co/rmBjPS4/background.jpg" alt="" style="width: 100%;">Star</img>
                <table>
                    <tr>
                        <td>
                            <p>test 7</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>test 07</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>test 07</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>test 07</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>test 07</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>



                </table>
            </div>
            <div id="menu" style="width: 360px;float: left;height: 350px;background-color: rgb(240, 200, 140);">
                <div id="round" style="width: 360px;height: 30px;background-color: lavenderblush;">

                </div>
                <div id="message" style="width: inherit;height: 90px;background-color: lightcoral;">

                </div>
                <div id="button" style="width: inherit;height: 30px;background-color: lawngreen;">
                    <button id="showWinner">Show Winner</button>

                </div>

            </div>
        </div>

        <div id="AIcard" style="widows: inherit;height: 350px;">
            <div class="card">
                <!-- <h5>Jam (7)</h5> -->
                <img src="https://i.ibb.co/rmBjPS4/background.jpg" alt="" style="width: 100%;">Star</img>
                <table>
                    <tr>
                        <td>
                            <p>test 7</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>test 07</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>test 07</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>test 07</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>test 07</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>



                </table>
            </div>

            <div class="card">
                <img src="https://i.ibb.co/rmBjPS4/background.jpg" alt="" style="width: 100%;">
                <table>
                    <tr>
                        <td>
                            <p>test 7</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>test 07</p>
                        </td>
                        <td>
                            <p>7</p>
                        </td>
                    </tr>
                </table>
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
            //helloJSONList();
            //helloWord("Student");
            getRoundNo();

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
            var xhr =createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundNo");
            if (!xhr) {
                alert("CORS not supported");
            }
            xhr.onload = function (e) {
                var responseText = xhr.response; // the text of the response
                document.getElementById("caren").innerHTML=responseText;
                //alert(responseText); // lets produce an alert
            };

            xhr.send();
        }

    </script>

</body>

</html>