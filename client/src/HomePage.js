import logo from "./design/ROAD_DOGS.png";

function HomePage() {
    return (<div className="homepage">
        <h1>Welcome to the DUNGEON MASTER'S LAIR</h1>
        <div className="row">
            <div className="col"></div>
            <div className="col-10">
                <p>Greetings adventures! We are ROAD DOGS and we have created this application for all those who love to play
                dungeons and dragons in their own way. We allow for lots of customization for our characters and game masters.
                When you register your new account you'll have access to create and maintain new characters, if you register as 
                a game master you will have access to our campaign features which allow for collecting players and having notes 
                for your campaign as well as have your own characters! Are you ready to begin your adventure? </p>
            </div>
            <div className="col"></div>
        </div>
        <a href="/login"><h2>Get Started</h2></a>
        <div>
            <img className="logo" id="logo" src={logo} alt="" />
        </div>
    </div>)

}
export default HomePage;