import React, { Component } from "react";
import shopping from "../../assets/images/logo/shopping-cart.png";
import "../../assets/css/global.css";

export default class Cart extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cart: props.cart
    }
  }

  // componentWillReceiveProps(nextProps) {
  //   console.log(nextProps)
  //   if (nextProps.cart.length !== this.state.cart.length) {
  //     console.log("TRUE");
  //   }
  // }

  render() {
    return (
      <div className="shopping-cart">
        <div className="badge-cart">{this.props.cart.length}</div>
        <img src={shopping} alt="cart" />
      </div>
    );
  }
}
