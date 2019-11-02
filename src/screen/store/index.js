import React, { Component } from "react";
import { Link } from "react-router-dom";
import "owl.carousel/dist/assets/owl.carousel.css";
import "owl.carousel/dist/assets/owl.theme.default.css";
import "../../assets/css/store.css";
import "hover.css";
import Cart from "../../components/cart";
import logo from "../../assets/images/logo/shopping.png";
import logo2 from "../../assets/images/logo/shopping2.png";
import Slider from "../../components/slider";
import Header from "../../components/header";
import ProductCart from "../../components/product";

export default class IndexScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cart: props.cart.cart
    }
  }

  updateCart = (data) => {
    this.props.setCart(data);
    let { cart } = this.state;
    this.setState({ cart: cart })
  }

  render() {
    let options = {
      0: {
        items: 1,
        nav: true
      },
      720: {
        items: 2,
        nav: true
      },
      1140: {
        items: 3,
        nav: true
      },
      1440: {
        items: 4,
        nav: true
      }
    };
    let { cart } = this.state;
    return (
      <div className="content">
        <Cart cart={cart} />
        <Header
          mainClass="content-inner logo sec-header"
          logo={logo}
          componentLeft={
            <div
              className="logo-title"
              style={{ padding: "0 5vmin", textAlign: "center" }}
            >
              <p className="home-title">SHOPPING</p>
              <p className="home-title">CENTER</p>
            </div>
          }
        />
        <Slider title="Bestsellers" options={options}>
          <ProductCart
            title="My Product"
            stars={4.5}
            onPress={() => this.updateCart(1)}
          />
        </Slider>
        <Header
          mainClass="content-inner logo sec-shop"
          logo={logo2}
          componentLeft={
            <div className="button btn-hover">
              <Link to="/">SHOP ALL</Link>
            </div>
          }
        />
        <Slider title="Best Stores" options={options}>
          <ProductCart title="Store" />
        </Slider>
      </div>
    );
  }
}
