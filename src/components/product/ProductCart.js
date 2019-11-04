import React from "react";
import { Link } from "react-router-dom";
import "owl.carousel/dist/assets/owl.carousel.css";
import "owl.carousel/dist/assets/owl.theme.default.css";
import bg1 from "../../assets/images/bg1.jpg";
import "../../assets/css/store.css";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import PropTypes from "prop-types";

library.add(fas, fab, far);

function ProductCart(props) {
  const renderStars = (stars = -1) => {
    let star = ["", "", "", "", ""];
    return star.map((item, index) => {
      return (
        <FontAwesomeIcon
          key={index}
          icon={[index <= (stars-1) ? "fas" : "far", "heart"]}
          color="black"
          style={{ margin: "0 2px", fontSize: "2vmin" }}
        />
      );
    });
  };
  
  return (
    <div className="card-product" style={props.style}>
      <div className="card-container">
        <img className="owl-lazy card-image" alt="item" src={bg1} />
        <div className="overlay">
          <Link to="/">Quick View</Link>
        </div>
      </div>
      <div className="card-rating">{renderStars(props.stars)}</div>
      <div className="card-title">{props.title}</div>
      <div>
        <div className="button btn-hover">
          <div onClick={props.onPress}>Add to bag</div>
        </div>
      </div>
    </div>
  );
}

export default ProductCart;

ProductCart.propTypes = {
  stars: PropTypes.number,
  title: PropTypes.string,
  onPress: PropTypes.func,
  style: PropTypes.object
};
