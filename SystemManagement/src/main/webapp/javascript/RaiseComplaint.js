//var config = {
//    curl: 'https://api.countrystatecity.in/v1/countries',
//    ckey: 'YnZzWGQzT01Makp1VmVkTDBRMXlSN2xYSlpzbEhEUVdwZkozZUQxVw==' // Replace with your actual API key
//};
//
//async function fetchAPI(endpoint) {
//    console.log(`Fetching data from endpoint: ${endpoint}`);
//    const response = await fetch(endpoint, {
//        headers: {
//            'X-CSCAPI-KEY': config.ckey
//        }
//    });
//    const data = await response.json();
//    console.log('Received data:', data);
//    return data;
//}
//
//async function loadCountries() {
//    console.log('Loading countries...');
//    const countries = await fetchAPI(config.curl);
//    const countrySelect = document.getElementById('countryName');
//    countrySelect.innerHTML = '<option selected disabled>Choose Country</option>'; // Clear any existing options
//
//    countries.forEach(country => {
//        let option = document.createElement('option');
//        option.value = country.iso2;
//        option.textContent = country.name;
//        countrySelect.appendChild(option);
//    });
//
//    console.log('Countries loaded:', countries);
//}
//
//async function loadStates(countryCode) {
//    console.log(`Loading states for country code: ${countryCode}`);
//    const endpoint = `https://api.countrystatecity.in/v1/countries/${countryCode}/states`;
//    const states = await fetchAPI(endpoint);
//    const stateSelect = document.getElementById('state');
//    stateSelect.innerHTML = '<option selected disabled>Choose State</option>'; // Clear any existing options
//
//    states.forEach(state => {
//        let option = document.createElement('option');
//        option.value = state.iso2;
//        option.textContent = state.name;
//        stateSelect.appendChild(option);
//    });
//
//    console.log(`States loaded for country ${countryCode}:`, states);
//}
//
//async function loadCities(stateCode, countryCode) {
//    console.log(`Loading cities for state code: ${stateCode}in country: ${countryCode}`);
//    const endpoint = `https://api.countrystatecity.in/v1/countries/${countryCode}/states/${stateCode}/cities`;
//    const cities = await fetchAPI(endpoint);
//    const citySelect = document.getElementById('city');
//    citySelect.innerHTML = '<option selected disabled>Choose City</option>'; // Clear any existing options
//
//    cities.forEach(city => {
//        let option = document.createElement('option');
//        option.value = city.name;
//        option.textContent = city.name;
//        citySelect.appendChild(option);
//    });
//
//    console.log(`Cities loaded for state ${stateCode}:`, cities);
//}
//
//// Call the function to fetch countries when the page loads
//window.onload = loadCountries;
//
//// Add event listener to load states when a country is selected
//document.addEventListener('DOMContentLoaded', function () {
//    const countrySelect = document.getElementById('countryName');
//    countrySelect.addEventListener('change', function () {
//        const countryCode = this.value;
//        if (countryCode) {
//            console.log(`Country selected: ${countryCode}`);
//            loadStates(countryCode);
//        }
//    });
//
//    const stateSelect = document.getElementById('state');
//    stateSelect.addEventListener('change', function () {
//        const stateCode = this.value;
//        const countryCode = document.getElementById('countryName').value;
//        if (stateCode) {
//            console.log(`State selected: ${stateCode}`);
//            loadCities(stateCode, countryCode);
//        }
//    });
//});



//var config = {
//    curl: 'https://api.countrystatecity.in/v1/countries',
//    ckey: 'YnZzWGQzT01Makp1VmVkTDBRMXlSN2xYSlpzbEhEUVdwZkozZUQxVw==' // Replace with your actual API key
//};
//
//async function fetchAPI(endpoint) {
// //   console.log(`Fetching data from endpoint: ${endpoint}`);
//    const response = await fetch(endpoint, {
//        headers: {
//            'X-CSCAPI-KEY': config.ckey
//        }
//    });
//    const data = await response.json();
//  //  console.log('Received data:', data);
//    return data;
//}
//
//async function loadCountries() {
//    console.log('Loading countries...');
//    const countries = await fetchAPI(config.curl);
//    const country = document.getElementById('countryName');
//    country.innerHTML = '<option selected disabled>Choose Country</option>'; // Clear any existing options
//
//    countries.forEach(country => {
//        let option = document.createElement('option');
//        option.value = country.iso2;
//        option.textContent = country.name;
//        option.dataset.name = country.name; // Store full name in dataset
//        countryName.appendChild(option);
//    });
//
//  //  console.log('Countries loaded:', countries);
//    console.log('NAME ------:',country.name,country.iso2);
//}
//
//async function loadStates(countryCode) {
//    console.log(`Loading states for country code------: ${countryCode}`);
//    const endpoint = `https://api.countrystatecity.in/v1/countries/${countryCode}/states`;
//    const states = await fetchAPI(endpoint);
//    const stateSelect = document.getElementById('state');
//    stateSelect.innerHTML = '<option selected disabled>Choose State</option>'; // Clear any existing options
//
//    states.forEach(state => {
//        let option = document.createElement('option');
//        option.value = state.iso2;
//        option.textContent = state.name;
//        option.dataset.name = state.name; // Store full name in dataset
//        stateSelect.appendChild(option);
//    });
//
//    console.log(`States loaded for country ${countryCode}:`, states);
//}
//
//async function loadCities(stateCode, countryCode) {
//   // console.log(`Loading cities for state code: ${stateCode} in country: ${countryCode}`);
//    const endpoint = `https://api.countrystatecity.in/v1/countries/${countryCode}/states/${stateCode}/cities`;
//    const cities = await fetchAPI(endpoint);
//    const citySelect = document.getElementById('city');
//    citySelect.innerHTML = '<option selected disabled>Choose City</option>'; // Clear any existing options
//
//    cities.forEach(city => {
//        let option = document.createElement('option');
//        option.value = city.name;
//        option.textContent = city.name;
//        citySelect.appendChild(option);
//    });
//
//    console.log(`Cities loaded for state ${stateCode}:`, cities);
//}
//
//// Call the function to fetch countries when the page loads
//window.onload = loadCountries;
//
//// Add event listener to load states when a country is selected
//document.addEventListener('DOMContentLoaded', function () {
//    const countrySelect = document.getElementById('countryName');
//    countrySelect.addEventListener('change', function () {
//        const countryCode = this.value;
//        const countryName = this.options[this.selectedIndex].dataset.name;
//        document.getElementById('hiddenCountryName').value = countryName; // Store full country name
//        console.log(`Country selected: ${countryCode} - ${countryName}`);
//        if (countryCode) {
//            loadStates(countryCode);
//        }
//    });
//
//    const stateSelect = document.getElementById('state');
//    stateSelect.addEventListener('change', function () {
//        const stateCode = this.value;
//        const stateName = this.options[this.selectedIndex].dataset.name;
//        const countryCode = document.getElementById('countryName').value;
//        document.getElementById('hiddenStateName').value = stateName; // Store full state name
//        console.log(`State selected: ${stateCode} - ${stateName}`);
//        if (stateCode) {
//            loadCities(stateCode, countryCode);
//        }
//    });
//});




var config = {
    curl: 'https://api.countrystatecity.in/v1/countries',
    ckey: 'YnZzWGQzT01Makp1VmVkTDBRMXlSN2xYSlpzbEhEUVdwZkozZUQxVw==' // Replace with your actual API key
};

async function fetchAPI(endpoint) {
    console.log(`Fetching data from endpoint: ${endpoint}`);
    try {
        const response = await fetch(endpoint, {
            headers: {
                'X-CSCAPI-KEY': config.ckey
            }
        });
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();
        console.log('Received data:', data);
        return data;
    } catch (error) {
        console.error('Fetch error:', error);
        return null; // or handle error as needed
    }
}

async function loadCountries() {
    console.log('Loading countries...');
    const countries = await fetchAPI(config.curl);
    if (!countries) {
        console.error('Failed to load countries.');
        return;
    }
    const countrySelect = document.getElementById('countryName');
    countrySelect.innerHTML = '<option selected disabled>Choose Country</option>'; // Clear any existing options

    countries.forEach(country => {
        let option = document.createElement('option');
        option.value = country.name;  // Use country name instead of iso2
        option.textContent = country.name;
        countrySelect.appendChild(option);
    });

    console.log('Countries loaded:', countries);
}

async function loadStates(countryName) {
    console.log(`Loading states for country name: ${countryName}`);
    const countries = await fetchAPI(config.curl);
    const country = countries.find(c => c.name === countryName);
    if (!country) {
        console.error(`Country not found: ${countryName}`);
        return;
    }
    const endpoint = `https://api.countrystatecity.in/v1/countries/${country.iso2}/states`;
    const states = await fetchAPI(endpoint);
    if (!states) {
        console.error(`Failed to load states for country name ${countryName}.`);
        return;
    }
    const stateSelect = document.getElementById('state');
    stateSelect.innerHTML = '<option selected disabled>Choose State</option>'; // Clear any existing options

    states.forEach(state => {
        let option = document.createElement('option');
        option.value = state.name;  // Use state name instead of iso2
        option.textContent = state.name;
        stateSelect.appendChild(option);
    });

    console.log(`States loaded for country ${countryName}:`, states);
}

async function loadCities(stateName, countryName) {
    console.log(`Loading cities for state name: ${stateName} in country: ${countryName}`);
    const countries = await fetchAPI(config.curl);
    const country = countries.find(c => c.name === countryName);
    if (!country) {
        console.error(`Country not found: ${countryName}`);
        return;
    }
    const endpointStates = `https://api.countrystatecity.in/v1/countries/${country.iso2}/states`;
    const states = await fetchAPI(endpointStates);
    const state = states.find(s => s.name === stateName);
    if (!state) {
        console.error(`State not found: ${stateName}`);
        return;
    }
    const endpointCities = `https://api.countrystatecity.in/v1/countries/${country.iso2}/states/${state.iso2}/cities`;
    const cities = await fetchAPI(endpointCities);
    if (!cities) {
        console.error(`Failed to load cities for state name ${stateName} in country ${countryName}.`);
        return;
    }
    const citySelect = document.getElementById('city');
    citySelect.innerHTML = '<option selected disabled>Choose City</option>'; // Clear any existing options

    cities.forEach(city => {
        let option = document.createElement('option');
        option.value = city.name;
        option.textContent = city.name;
        citySelect.appendChild(option);
    });

    console.log(`Cities loaded for state ${stateName}:`, cities);
}

// Call the function to fetch countries when the page loads
window.onload = loadCountries;

// Add event listener to load states when a country is selected
document.addEventListener('DOMContentLoaded', function () {
    const countrySelect = document.getElementById('countryName');
    countrySelect.addEventListener('change', function () {
        const countryName = this.value;
        if (countryName) {
            console.log(`Country selected: ${countryName}`);
            loadStates(countryName);
        }
    });

    // Add event listener to load cities when a state is selected
    const stateSelect = document.getElementById('state');
    stateSelect.addEventListener('change', function () {
        const stateName = this.value;
        const countryName = document.getElementById('countryName').value;
        if (stateName) {
            console.log(`State selected: ${stateName}`);
            loadCities(stateName, countryName);
        }
    });
});