import React, { Component } from "react";
import PropTypes from "prop-types";
import OwlCarousel from "react-owl-carousel";

export default class Slider extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="content-inner">
        <div className="title">{this.props.title}</div>
        <OwlCarousel
          className="owl-theme"
          loop
          margin={10}
          nav
          items={4}
          lazyLoad
          style={{ padding: "0 10vmin" }}
          responsive={this.props.options}
        >
          {this.props.children}
        </OwlCarousel>
      </div>
    );
  }
}

Slider.propTypes = {
  title: PropTypes.string,
  options: PropTypes.object
};
