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
    console.log(`Loading cities for state code: ${stateCode}in country: ${countryCode}`);
    const endpoint = `https://api.countrystatecity.in/v1/countries/${countryCode}/states/${stateCode}/cities`;
    const cities = await fetchAPI(endpoint);
    const citySelect = document.getElementById('city');
    citySelect.innerHTML = '<option selected disabled>Choose City</option>'; // Clear any existing options

    cities.forEach(city => {
        let option = document.createElement('option');
        option.value = city.name;
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
        if (stateCode) {
            console.log(`State selected: ${stateCode}`);
            loadCities(stateCode, countryCode);
        }
    });
});