<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>System Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .oval-btn {
            border-radius: 50px; /* Adjust the value as needed for an oval shape */
            padding: 10px 20px;  /* Adjust the padding to control the button size */
        }
    </style>

    <script>
        var config = {
            curl: 'https://api.countrystatecity.in/v1/countries',
            ckey: 'YnZzWGQzT01Makp1VmVkTDBRMXlSN2xYSlpzbEhEUVdwZkozZUQxVw==' // Replace with your actual API key
        };

        async function fetchAPI(endpoint) {
            console.log(`Fetching data from endpoint: ${endpoint}`);
            const response = await fetch(endpoint, {
                headers: {
                    'X-CSCAPI-KEY': config.ckey
                }
            });
            const data = await response.json();
            console.log('Received data:', data);
            return data;
        }

        async function loadCountries() {
            console.log('Loading countries...');
            const countries = await fetchAPI(config.curl);
            const countrySelect = document.getElementById('countryName');
            countrySelect.innerHTML = '<option selected disabled>Choose Country</option>'; // Clear any existing options

            countries.forEach(country => {
                let option = document.createElement('option');
                option.value = country.iso2;
                option.textContent = country.name;
                countrySelect.appendChild(option);
            });

            console.log('Countries loaded:', countries);
        }

        async function loadStates(countryCode) {
            console.log(`Loading states for country code: ${countryCode}`);
            const endpoint = `https://api.countrystatecity.in/v1/countries/${countryCode}/states`;
            const states = await fetchAPI(endpoint);
            const stateSelect = document.getElementById('state');
            stateSelect.innerHTML = '<option selected disabled>Choose State</option>'; // Clear any existing options

            states.forEach(state => {
                let option = document.createElement('option');
                option.value = state.iso2;
                option.textContent = state.name;
                stateSelect.appendChild(option);
            });

            console.log(`States loaded for country ${countryCode}:`, states);
        }

        async function loadCities(stateCode, countryCode) {
            console.log(`Loading cities for state code: ${stateCode} in country: ${countryCode}`);
            const endpoint = `https://api.countrystatecity.in/v1/countries/${countryCode}/states/${stateCode}/cities`;
            const cities = await fetchAPI(endpoint);
            const citySelect = document.getElementById('city');
            citySelect.innerHTML = '<option selected disabled>Choose City</option>'; // Clear any existing options

            cities.forEach(city => {
                let option = document.createElement('option');
                option.value = city.id;
                option.textContent = city.name;
                citySelect.appendChild(option);
            });

            console.log(`Cities loaded for state ${stateCode}:`, cities);
        }

        // Call the function to fetch countries when the page loads
        window.onload = loadCountries;

        // Add event listener to load states when a country is selected
        document.addEventListener('DOMContentLoaded', function () {
            const countrySelect = document.getElementById('countryName');
            countrySelect.addEventListener('change', function () {
                const countryCode = this.value;
                if (countryCode) {
                    console.log(`Country selected: ${countryCode}`);
                    loadStates(countryCode);
                }
            });

            const stateSelect = document.getElementById('state');
            stateSelect.addEventListener('change', function () {
                const stateCode = this.value;
                const countryCode = document.getElementById('countryName').value;
                if (stateCode && countryCode) {
                    console.log(`State selected: ${stateCode}`);
                    loadCities(stateCode, countryCode);
                }
            });
        });
    </script>

</head>
<body>
    <nav class="navbar navbar-dark bg-info">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
                </a>
                <a class="navbar-brand text-dark" href="index.jsp"><b>Home</b></a>
                <a class="navbar-brand text-dark" href="SignIn.jsp"><b>SignIn</b></a>
            </div>
        </div>
    </nav>

    <div class="container mt-5 mb-5 d-flex justify-content-center">
        <div class="card p-4">
            <div class="card-body">
                <h3><b><center>Raise Complaints</center></b></h3>
            </div>
             <div class="card-body text-dark">
                        <form action="sign-up" method="post">
                            <div class="text-success"><strong>${msg}</strong></div>
                            <span style="color:red;">
                                <c:forEach items="${errors}" var="objectError">
                                    ${objectError.defaultMessage}<br>
                                </c:forEach>
                            </span>

                    <div class="row mb-3">
                        <span id="complaintTypeError"></span>
                        <label for="complaintType" class="form-label"><b>Complaint Type:</b></label>
                        <select class="form-select custom-select-width" id="complaintType" name="complaintType">
                            <option value="0" ${raiseComplaintDto.complaintType == null ? 'selected' : ''}>Select</option>
                            <option value="Electric Problem" ${raiseComplaintDto.complaintType == 'Electric Problem' ? 'selected' : ''}>Electric Problem</option>
                            <option value="Water Supply Issue" ${raiseComplaintDto.complaintType == 'Water Supply Issue' ? 'selected' : ''}>Water Supply Issue</option>
                            <option value="Network Issue" ${raiseComplaintDto.complaintType == 'Network Issue' ? 'selected' : ''}>Network Issue</option>
                            <option value="Solar Heater Problem" ${raiseComplaintDto.complaintType == 'Solar Heater Problem' ? 'selected' : ''}>Solar Heater Problem</option>
                            <option value="Geezer Issue" ${raiseComplaintDto.complaintType == 'Geezer Issue' ? 'selected' : ''}>Geezer Issue</option>
                        </select><br>
                    </div>

                    <!-- Country -->
                    <div class="row mb-3">
                        <span id="countryNameError"></span>
                        <label for="countryName" class="form-label"><b>Country:</b></label>
                        <select class="form-select custom-select-width" id="countryName" name="country" placeholder="Enter country">
                            <!-- Countries will be loaded here by JavaScript -->
                        </select><br>
                    </div>

                    <!-- State -->
                    <div class="row mb-3">
                        <span id="stateNameError"></span>
                        <label for="state" class="form-label"><b>State:</b></label>
                        <select class="form-select custom-select-width" id="state" name="state">
                            <!-- States will be loaded here by JavaScript -->
                        </select><br>
                    </div>

                    <!-- City -->
                    <div class="row mb-3">
                        <span id="cityNameError"></span>
                        <label for="city" class="form-label"><b>City:</b></label>
                        <select class="form-select custom-select-width" id="city" name="city" placeholder="Enter city">
                            <!-- Cities will be loaded here by JavaScript -->
                        </select><br>
                    </div>

                    <div class="row mb-3">
                        <span id="areaError"></span><br>
                        <label for="area" class="form-label"><b>Area:</b></label>
                        <input type="text" class="form-control" id="area" name="area" placeholder="Enter area">
                    </div>

                    <div class="mb-3">
                        <span id="errorAddress"></span><br>
                        <b>Address</b>
                        <label for="address" class="form-floating"></label>
                        <textarea class="form-control" placeholder="Enter address" id="address" style="height: 80px" name="address">${jobFormDTO.address}</textarea>
                    </div>

                    <div class="mb-3">
                        <span id="descriptionError" class="text-danger"></span>
                        <b>Description:</b>
                        <div class="form-floating">
                            <textarea class="form-control" placeholder="Leave a comment here" name="description" id="description" style="height:100px" onblur="updateDescriptionCount()" maxlength="1000" onblur="validateDescription()">${complaint.description}</textarea>
                            <label for="description">Description</label>
                </div>
            </div>

                <div>
                    <center><input type="submit" id="submit" value="Apply" class="btn btn-primary oval-btn"></center>
                </div>

                <div class="mb-3">
                    <center><p>Already have an account? <a href="SignIn.jsp" class="link-primary"><strong>SignIn Here</strong></a></p></center>
                </div>
            </form>
        </div>
    </div>
    </div>

    <!-- Including the JavaScript file after the DOM is loaded -->
</body>
</html>