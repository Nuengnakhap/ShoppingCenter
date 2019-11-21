import React, { Component } from "react";
import ImageGallery from "react-image-gallery";
import "react-image-gallery/styles/css/image-gallery.css";
import "../../assets/css/global.css";
import "../../assets/css/store.css";
import "../../assets/css/detail.css";

export default class Details extends Component {
  render() {
    const images = [
      {
        original: "https://picsum.photos/id/1018/1000/600/",
        thumbnail: "https://picsum.photos/id/1018/250/150/"
      },
      {
        original: "https://picsum.photos/id/1015/1000/600/",
        thumbnail: "https://picsum.photos/id/1015/250/150/"
      },
      {
        original: "https://picsum.photos/id/1019/1000/600/",
        thumbnail: "https://picsum.photos/id/1019/250/150/"
      }
    ];

    return (
      <div className="content-inner detail-content">
        <ImageGallery items={images} showBullets />
        <div className="detail-text">
          <div className="title">TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest</div>
        </div>
      </div>
    );
  }
}
