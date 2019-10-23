import React, { Component } from "react";
import { storage } from "../../libs/firebase";

export default class ImageUpload extends Component {
  constructor(props) {
    super(props);
    this.state = {
      image: null,
      url: "",
      progress: 0,
      transferred: ""
    };
  }

  handleChange = e => {
    if (e.target.files[0]) {
      const image = e.target.files[0];
      console.log(image);
      this.setState({ image });
    }
  };

  handleUpload = () => {
    const { image } = this.state;
    const uploadTask = storage.ref(`images/${image.name}`).put(image);
    uploadTask.on(
      "state_changed",
      snapshot => {
        // progress function ...
        let transferred = snapshot.bytesTransferred / snapshot.totalBytes;
        const progress = Math.round(transferred * 100);
        this.setState({
          progress: progress,
          transferred: `${(snapshot.bytesTransferred / 1048576).toFixed(
            2
          )} / ${(snapshot.totalBytes / 1048576).toFixed(2)} MB`
        });
      },
      error => {
        // Error function ...
        console.log(error);
      },
      () => {
        // complete function ...
        storage
          .ref("images")
          .child(image.name)
          .getDownloadURL()
          .then(url => {
            this.setState({ url });
          });
      }
    );
  };

  render() {
    return (
      <div>
        <div className="custom-file mt-2 mb-2">
          <input
            type="file"
            className="custom-file-input"
            id="file"
            name="file"
            required
            multiple
          />
          <label className="custom-file-label" htmlFor="file" id="fire-upload">
            Choose file
          </label>
        </div>
        <div>
          <progress
            id="progressBar"
            value={this.state.progress}
            max="100"
            style={{ width: "100%" }}
          />
          <p id="status"></p>
          <p id="loaded_n_total">{this.state.transferred}</p>
        </div>
        <input type="file" onChange={this.handleChange} />
        <button onClick={this.handleUpload}>Upload</button>
        <br />
        <br />
        <img
          src={this.state.url || "https://via.placeholder.com/400x300"}
          alt="Uploaded Images"
          height="300"
          width="400"
        />
      </div>
    );
  }
}
