import React, { Component } from "react";
import logo from "../../assets/images/logo/shopping.png";
import "../../assets/css/home.css"

export default class HomeScreen extends Component {
  render() {
    return (
      <div>
        <header className="App-header">
          <div className="row Div-logo">
            <div style={{ padding: "0 5vmin", textAlign: "center" }}>
              <p className="home-title">SHOPPING</p>
              <p className="home-title">CENTER</p>
              <button className="shop-button btn-hover">Go to shop</button>
            </div>
            <div>
              <img src={logo} className="App-logo center-images" style={{ padding: "0 5vmin" }} alt="logo" />
            </div>
          </div>
        </header>
      </div>
    );
  }
}
