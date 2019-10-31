import React from "react";
import PropTypes from "prop-types";
import OwlCarousel from "react-owl-carousel";

function Slider(props) {
  return (
    <div className="content-inner">
      <div className="title">{props.title}</div>
      <OwlCarousel
        className="owl-theme"
        loop
        margin={10}
        nav
        items={4}
        lazyLoad
        style={{ padding: "0 10vmin" }}
        responsive={props.options}
      >
        {props.children}
      </OwlCarousel>
    </div>
  );
}

export default Slider;

Slider.propTypes = {
  title: PropTypes.string,
  options: PropTypes.object
};
