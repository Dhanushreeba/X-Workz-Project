<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>ComplaintRise</title>
    <style>
        .error {
            color: red;
            font-size: 0.9em;
        }
    </style>
    <script src="/crisisManagement/jscript/CrisisSignUp.js"></script>
</head>
<body>
    <nav class="navbar navbar-light" style="background-color:black">
        <div class="container" style="display: flex; flex-direction: row;">
            <a class="navbar-brand" href="#">
                <img src="/crisisManagement/logo/xworklogo.png" alt="xworkz" width="140" height="70">
            </a>
            <a class="navbar-brand" href="index.jsp" style="color: aliceblue;"><b>Dashboard</b></a>
        </div>
    </nav>
    <div class="container mt-3" style="max-width: 600px;">
        <div class="card p-3">
            <div class="card-header">
                <center><h4><b>ComplaintRise</b></h4></center>
            </div>
            <div class="card-body">
                <form action="complaintRise" method="post" id="CrisisSignUp" onsubmit="return validateForm()">
                    <div class="form-group mb-2">
                        <label>Complaint Type:</label>
                        <select class="form-control" id="complaintType" name="complaintType">
                            <option value="">Select Complaint Type</option>
                            <option value="electricity">Electricity</option>
                            <option value="water">Water</option>
                            <option value="road">Road</option>
                            <option value="drainage">Drainage</option>
                            <option value="health">Health</option>
                            <option value="education">Education</option>
                            <option value="environment">Environment</option>
                            <option value="traffic">Traffic</option>
                            <option value="parking">Parking</option>
                            <option value="noise">Noise Pollution</option>
                            <option value="security">Security Concerns</option>
                            <option value="housing">Housing Issues</option>
                        </select>
                        <span id="complaintTypeError" class="error"></span>
                    </div>
                    <div class="form-group mb-2">
                        <label for="country">Country:</label>
                        <select class="form-control country" id="country" name="country" onchange="loadStates()">
                            <option value="">Select Country</option>
                        </select>
                        <span id="countryError" class="error"></span>
                    </div>
                    <div class="form-group mb-2">
                        <label for="state">State:</label>
                        <select class="form-control state" id="state" name="state" onchange="loadCities()" disabled>
                            <option value="">Select State</option>
                        </select>
                        <span id="stateError" class="error"></span>
                    </div>
                    <div class="form-group mb-2">
                        <label for="city">City:</label>
                        <select class="form-control city" id="city" name="city">
                            <option value="">Select City</option>
                        </select>
                        <span id="cityError" class="error"></span>
                    </div>
                    <div class="form-group mb-2">
                        <label for="message">Description:</label>
                        <textarea class="form-control" id="message" name="message" rows="3" maxlength="1000" oninput="updateCounter()"></textarea>
                        <div class="counter">
                            <span id="charCount">0</span> / 1000 characters used
                        </div>
                        <span id="messageError" class="error"></span>
                    </div>
                    <div class="form-group mb-2">
                        <label for="address" class="form-label">Address:</label>
                        <input type="text" class="form-control" id="address" name="address">
                        <span id="addressError" class="error"></span>
                    </div>
                    <div>
                        <input type="submit" class="btn btn-dark" value="Rise Complaint" name="RiseComplaint">
                        <input type="reset" class="btn btn-dark" style="width: 130px;" value="Reset" name="Reset">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        var config = {
            countriesUrl: 'https://api.countrystatecity.in/v1/countries',
            statesUrl: 'https://api.countrystatecity.in/v1/countries/[ciso]/states',
            citiesUrl: 'https://api.countrystatecity.in/v1/countries/[ciso]/states/[siso]/cities',
            ckey: 'ODIzTTVJUGVIVWdQT1ZJbk1McU50RnJ0ZmtTUVNBcERseFdTb25SSA=='
        };

        var countrySelect = document.querySelector('.country'),
            stateSelect = document.querySelector('.state'),
            citySelect = document.querySelector('.city'),
            submitBtn = document.querySelector('input[name="RiseComplaint"]');

        function updateCounter() {
            var message = document.getElementById("message");
            var charCount = document.getElementById("charCount");
            var maxLength = message.getAttribute("maxlength");
            var currentLength = message.value.length;

            charCount.textContent = currentLength;
        }

        function validateForm() {
            var complaintType = document.getElementById('complaintType').value;
            var country = document.getElementById('country').value;
            var state = document.getElementById('state').value;
            var city = document.getElementById('city').value;
            var message = document.getElementById('message').value;
            var address = document.getElementById('address').value;

            var valid = true;

            if (complaintType === '') {
                document.getElementById('complaintTypeError').textContent = 'Please select a complaint type';
                valid = false;
            } else {
                document.getElementById('complaintTypeError').textContent = '';
            }

            if (country === '') {
                document.getElementById('countryError').textContent = 'Please select a country';
                valid = false;
            } else {
                document.getElementById('countryError').textContent = '';
            }

            if (state === '') {
                document.getElementById('stateError').textContent = 'Please select a state';
                valid = false;
            } else {
                document.getElementById('stateError').textContent = '';
            }

            if (city === '') {
                document.getElementById('cityError').textContent = 'Please select a city';
                valid = false;
            } else {
                document.getElementById('cityError').textContent = '';
            }

            if (message === '') {
                document.getElementById('messageError').textContent = 'Please enter a description';
                valid = false;
            } else {
                document.getElementById('messageError').textContent = '';
            }

            if (address === '') {
                document.getElementById('addressError').textContent = 'Please enter an address';
                valid = false;
            } else {
                document.getElementById('addressError').textContent = '';
            }

            return valid;
        }

        function loadCountries() {
            fetch(config.countriesUrl, { headers: { "X-CSCAPI-KEY": config.ckey } })
                .then(response => response.json())
                .then(data => {
                    data.forEach(country => {
                        const option = document.createElement('option');
                        option.value = country.iso2;
                        option.textContent = country.name;
                        countrySelect.appendChild(option);
                    });
                })
                .catch(error => console.error('Error loading countries:', error));

            stateSelect.disabled = true;
            citySelect.disabled = true;
            stateSelect.style.pointerEvents = 'none';
            citySelect.style.pointerEvents = 'none';
        }

        function loadStates() {
            stateSelect.disabled = false;
            citySelect.disabled = true;
            stateSelect.style.pointerEvents = 'auto';
            citySelect.style.pointerEvents = 'none';

            const selectedCountryCode = countrySelect.value;
            stateSelect.innerHTML = '<option value="">Select State</option>';
            citySelect.innerHTML = '<option value="">Select City</option>';

            fetch(config.statesUrl.replace('[ciso]', selectedCountryCode), { headers: { "X-CSCAPI-KEY": config.ckey } })
                .then(response => response.json())
                .then(data => {
                    data.forEach(state => {
                        const option = document.createElement('option');
                        option.value = state.iso2;
                        option.textContent = state.name;
                        stateSelect.appendChild(option);
                    });
                })
                .catch(error => console.error('Error loading states:', error));
        }

        function loadCities() {
            citySelect.disabled = false;
            citySelect.style.pointerEvents = 'auto';

            const selectedCountryCode = countrySelect.value;
            const selectedStateCode = stateSelect.value;
            citySelect.innerHTML = '<option value="">Select City</option>';

            fetch(config.citiesUrl.replace('[ciso]', selectedCountryCode).replace('[siso]', selectedStateCode), { headers: { "X-CSCAPI-KEY": config.ckey } })
                .then(response => response.json())
                .then(data => {
                    data.forEach(city => {
                        const option = document.createElement('option');
                        option.value = city.name;
                        option.textContent = city.name;
                        citySelect.appendChild(option);
                    });
                })
                .catch(error => console.error('Error loading cities:', error));
        }

        loadCountries();
    </script>
</body>
</html>