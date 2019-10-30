import React, { Component } from "react";
import OwlCarousel from "react-owl-carousel";
import { Link } from "react-router-dom";
import "owl.carousel/dist/assets/owl.carousel.css";
import "owl.carousel/dist/assets/owl.theme.default.css";
import bg1 from "../../assets/images/bg1.jpg";
import "../../assets/css/store.css";
import "hover.css";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import Cart from "../../components/cart";
import logo from "../../assets/images/logo/shopping.png";

library.add(fas, fab, far);

export default class StoreScreen extends Component {
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
    return (
      <div className="content">
        <Cart />
        <div className="content-inner logo">
          <div style={{ padding: "0 5vmin", textAlign: "center" }}>
            <p className="home-title">SHOPPING</p>
            <p className="home-title">CENTER</p>
          </div>
          <div>
            <img
              src={logo}
              className="center-images"
              style={{ padding: "0 5vmin" }}
              alt="logo"
            />
          </div>
        </div>
        <div className="content-inner">
          <div className="title">Bestsellers</div>
          <OwlCarousel
            className="owl-theme"
            loop
            margin={10}
            nav
            items={4}
            lazyLoad
            style={{ padding: "0 10vmin" }}
            responsive={options}
          >
            <div className="card-product">
              <div className="card-container">
                <img className="owl-lazy card-image" alt="item" src={bg1} />
                <div className="overlay">
                  <Link to="/">Quick View</Link>
                </div>
              </div>
              <div className="card-rating">
                <FontAwesomeIcon
                  icon={["far", "heart"]}
                  color="black"
                  size="xs"
                  style={{ margin: "0 2px" }}
                />
                <FontAwesomeIcon
                  icon={["far", "heart"]}
                  color="black"
                  size="xs"
                  style={{ margin: "0 2px" }}
                />
                <FontAwesomeIcon
                  icon={["far", "heart"]}
                  color="black"
                  size="xs"
                  style={{ margin: "0 2px" }}
                />
                <FontAwesomeIcon
                  icon={["far", "heart"]}
                  color="black"
                  size="xs"
                  style={{ margin: "0 2px" }}
                />
                <FontAwesomeIcon
                  icon={["far", "heart"]}
                  color="black"
                  size="xs"
                  style={{ margin: "0 2px" }}
                />
              </div>
              <div className="card-title">My Product</div>
              <div>
                <div className="button btn-hover">
                  <Link to="/">Add to bag</Link>
                </div>
              </div>
            </div>
          </OwlCarousel>
        </div>
      </div>
    );
  }
}
