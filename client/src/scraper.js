let spells = {};

function getSpells(){
    fetch("http://www.dnd5eapi.co/api/spells")
    .then(response => {
        if (response.status === 200){
            return response.json();
        }
        else {
            alert("Something went wrong fetching")
        }
    }).then(data => spells = data)
    .catch(rejection => alert(rejection))
}
getSpells();
console.log(spells)