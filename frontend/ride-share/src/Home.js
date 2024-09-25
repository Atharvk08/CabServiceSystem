import React, { useState } from "react";
import {
  MapContainer,
  TileLayer,
  Marker,
  Popup,
  useMapEvents,
} from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import { getDistance } from "geolib";

const defaultPosition = [51.505, -0.09]; // Default position for the map (London)

function LocationMarker({ position, setPosition }) {
  useMapEvents({
    click(e) {
      setPosition(e.latlng);
    },
  });

  return position === null ? null : (
    <Marker position={position}>
      <Popup>
        You clicked here: <br />
        Lat: {position.lat}, Lng: {position.lng}
      </Popup>
    </Marker>
  );
}

function App() {
  const [position1, setPosition1] = useState(null);
  const [position2, setPosition2] = useState(null);
  const [distance, setDistance] = useState(null);

  const calculateDistance = () => {
    if (position1 && position2) {
      const dist = getDistance(
        { latitude: position1.lat, longitude: position1.lng },
        { latitude: position2.lat, longitude: position2.lng }
      );
      setDistance(dist);
    }
  };

  return (
    <div>
      <h2>OpenStreetMap with Distance Calculation</h2>
      <div style={{ height: "500px", width: "100%" }}>
        <MapContainer
          center={defaultPosition}
          zoom={13}
          style={{ height: "100%", width: "100%" }}
        >
          <TileLayer
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
          />
          {position1 == null ? (
            <LocationMarker position={position1} setPosition={setPosition1} />
          ) : (
            <LocationMarker position={position2} setPosition={setPosition2} />
          )}
        </MapContainer>
      </div>

      <div style={{ marginTop: "20px" }}>
        <button onClick={calculateDistance} disabled={!position1 || !position2}>
          Calculate Distance
        </button>

        {distance !== null && (
          <p>Distance between the two points: {distance} meters</p>
        )}
      </div>
    </div>
  );
}

export default App;
