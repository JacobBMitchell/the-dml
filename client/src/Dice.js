import blank from "./design/d20Blank.png";
function Dice() {
    function sleep(miliseconds) {
        var currentTime = new Date().getTime();
     
        while (currentTime + miliseconds >= new Date().getTime()) {
        }
     }

    function roll(num) {
        let outcome = Math.floor(Math.random() * (num) + 1);
        let die = document.getElementById("roll");
        die.innerHTML = "";
        
        let blankSpinning = [];
        const blankTiming = {
            duration: 2000,
            iterations: 1
        }
        
        for (let index = -5; index < Math.floor(Math.random()*15); index++) {
            blankSpinning.push({transform: "rotate(" + Math.floor(Math.random()*360) + "deg)"});

            // blank.setAttribute("style", "transform: rotate(" + Math.floor(Math.random()*360) + "deg)"); //does not work here
            // sleep(150); //this works
            // console.log(index);
        }
        console.log(blankSpinning);
        console.log(blankTiming);
        let blank = document.getElementById("blank");
        blank.animate(blankSpinning,blankTiming);
        // blank.setAttribute("style", "transform: rotate(" + 20 + "deg)"); //works here
        
        die.innerHTML = outcome;
    }

    return (<div className="dice">
        <div className="btn-group">
            <button type="button" className="btn btn-danger" onClick={() => roll(20)}>Roll D20</button>
            <button type="button" className="btn btn-danger dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
                <span className="visually-hidden">Toggle Dropdown</span>
            </button>
            <ul className="dropdown-menu">
                <li><a className="dropdown-item" onClick={() => roll(4)}>D4</a></li>
                <li><a className="dropdown-item" onClick={() => roll(6)}>D6</a></li>
                <li><a className="dropdown-item" onClick={() => roll(8)}>D8</a></li>
                <li><a className="dropdown-item" onClick={() => roll(10)}>D10</a></li>
                <li><a className="dropdown-item" onClick={() => roll(12)}>D12</a></li>
                <li><a className="dropdown-item" onClick={() => roll(100)}>D100</a></li>
            </ul>
        </div>

        <div className="diceImg">
            <img className="blank" id="blank" src={blank} alt="" />
            <span className="roll" id="roll"></span>
        </div>
    </div>)
}
export default Dice;