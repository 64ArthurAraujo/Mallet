const searchBar = document.getElementById("search-bar")

let result = null;

fetchSearchSuggestions()

function doAutoComplete(typedContent) {
	
	console.log(typedContent.target.value.length)
	
	if (result != null) {
		let cachedSearches = result.replace('[', '').replace(']', '').split(',')		
		
		if (typedContent.target.value.length > 0) {
			cachedSearches.forEach((searchEntry) => {
				if (searchEntry.startsWith(typedContent.target.value)) {
					showAutoCompleteString( searchEntry )
				}
			})
		} else {
			showAutoCompleteString("")
		}
		
		console.log(cachedSearches)
	}
}

function showAutoCompleteString(searchEntry, typedContent) {
	let paragraph = document.getElementById("autocomplete-string")
	
	paragraph.innerText = searchEntry
}

function fetchSearchSuggestions() {
	const url = 'http://localhost:8080/Mallet/SearchCompleter'
	
	const xhr = new XMLHttpRequest()
	xhr.open('POST', url)
	xhr.onload = () => result = xhr.response
	
	xhr.send()
}

searchBar.addEventListener("input", doAutoComplete)

