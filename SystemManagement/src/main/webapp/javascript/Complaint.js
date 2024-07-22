//var config = {
//    curl: 'https://api.countrystatecity.in/v1/countries',
//    ckey: 'YnZzWGQzT01Makp1VmVkTDBRMXlSN2xYSlpzbEhEUVdwZkozZUQxVw==' // Replace with your actual API key
//};
//
//var countriesList = [];
//var statesList = [];
//var citiesList = [];
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
//function getISO2code(countryName){
//    countriesList.forEach(country => {
//   console.log(country)
//           if(countryName === country.name){
//                 console.log("Found "+country.iso2+" "+country.name)
//                 return country.iso2;
//           }
//
//    });
//}
//
//async function loadCountries() {
//    console.log('Loading countries...');
//    const countries = await fetchAPI(config.curl);
//    const countrySelect = document.getElementById('countryName');
//    countrySelect.innerHTML = '<option selected disabled>Choose Country</option>'; // Clear any existing options
//
//    countriesList = countries;
//    countries.forEach(country => {
//        let option = document.createElement('option');
//        option.value = country.name;
//        option.textContent = country.name;
//        countrySelect.appendChild(option);
//    });
//
//    console.log('Countries loaded:', countries);
//}
//
//
//
//
//async function loadStates(element) {
//    const cname = element.value;
//
//    console.log(`Loading states for country code: ${cname}`);
//
//    let countryISO2='';
//
//    countriesList.forEach(country => {
//               if(cname === country.name){
//                     console.log("Found "+country.iso2+" "+country.name)
//                     countryISO2 = country.iso2;
//               }
//        });
//
//    console.log(countryISO2);
//
//    const endpoint = `https://api.countrystatecity.in/v1/countries/${countryISO2}/states`;
//    const states = await fetchAPI(endpoint);
//
//    statesList=states;
//
//    const stateSelect = document.getElementById('state');
//    stateSelect.innerHTML = '<option selected disabled>Choose State</option>'; // Clear any existing options
//
//    states.forEach(state => {
//        let option = document.createElement('option');
//        option.value = state.name;
//        option.textContent = state.name;
//        stateSelect.appendChild(option);
//    });
//
//   // console.log(`States loaded for country ${countryCode}:`, states);
//
//}
//
//
//
//
//async function loadCities(state, country) {
//
//let stateName=state.value;
//let countryName=country.value;
//console.log(stateName,countryName)
//
//
//
//    console.log(`Loading cities for state code: ${stateName} in country: ${countryName}`);
//        let countryISO2='';
//
//        countriesList.forEach(country => {
//                   if(countryName === country.name){
//                         console.log("Found "+country.iso2+" "+country.name)
//                         countryISO2 = country.iso2;
//                   }
//            });
//
//            let stateIso2='';
//
//            statesList.forEach(state => {
//                               if(stateName === state.name){
//                                     console.log("Found "+state.iso2+" "+state.name)
//                                     stateIso2 = state.iso2;
//
//                               }
//                        });
//
//    const endpoint = `https://api.countrystatecity.in/v1/countries/${countryISO2}/states/${stateIso2}/cities`;
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
//    console.log(`Cities loaded for state ${stateName}:`, cities);
//}
//
//// Call the function to fetch countries when the page loads
//window.onload = loadCountries;
//
// //Add event listener to load states when a country is selected
////document.addEventListener('DOMContentLoaded', function () {
////    const countrySelect = document.getElementById('countryName');//changed
////    countrySelect.addEventListener('change', function () {
////        const countryCode = this.value;
////        if (countryCode) {
////            console.log(`Country selected: ${countryCode}`);
////            loadStates(countryCode);
////        }
////    });
//
////    const stateSelect = document.getElementById('state');
////    stateSelect.addEventListener('change', function () {
////        const stateCode = this.value;
////        const countryCode = document.getElementById('countryName').value;
////        if (stateCode) {
////            console.log(`State selected: ${stateCode}`);
////            loadCities(stateCode, countryCode);
////        }
////    });
////});


