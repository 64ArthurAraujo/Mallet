const searchBar = document.getElementById("search-bar")

let result = null;

function doAutoComplete(typedContent) {
	fetchSearchSuggestions()
	console.log(result)
}

function fetchSearchSuggestions() {
	const url = 'http://localhost:8080/Mallet/SearchCompleter'
	
	const xhr = new XMLHttpRequest()
	xhr.open('GET', url)
	xhr.responseType = 'json'
	xhr.onload = () => result = xhr.response
	
	xhr.send()
}

searchBar.addEventListener("input", doAutoComplete)

