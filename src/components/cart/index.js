import React, { Component } from "react";
import shopping from "../../assets/images/logo/shopping-cart.png";
import "../../assets/css/global.css";

export default class Cart extends Component {
  render() {
    return (
      <div className="shopping-cart">
        <div className="badge-cart">0</div>
        <img src={shopping} alt="cart" />
      </div>
    );
  }
}
