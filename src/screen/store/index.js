import React, { Component } from "react";
import OwlCarousel from "react-owl-carousel";
import "owl.carousel/dist/assets/owl.carousel.css";
import "owl.carousel/dist/assets/owl.theme.default.css";
import bg1 from "../../assets/images/bg1.jpg";
import bg2 from "../../assets/images/bg2.jpg";
import bg3 from "../../assets/images/bg3.jpg";
import "../../assets/css/store.css";
import "hover.css"

export default class StoreScreen extends Component {
  render() {
    return (
      <div className="content">
        <OwlCarousel
          className="owl-theme"
          loop
          margin={10}
          nav
          items={3}
          lazyLoad
          style={{ padding: "10vmin" }}
        >
          <div className="item">
            <img className="owl-lazy hvr-float" alt="item" src={bg1} />
          </div>
          <div className="item">
            <img className="owl-lazy hvr-float" alt="item" src={bg2} />
          </div>
          <div className="item">
            <img className="owl-lazy hvr-float" alt="item" src={bg3} />
          </div>
        </OwlCarousel>
      </div>
    );
  }
}
