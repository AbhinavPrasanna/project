import React from 'react';
import { Camera } from 'expo-camera';
import * as FaceDetector from 'expo-face-detector';
import { View, Text, StyleSheet, Image } from 'react-native';
import glasses from '../../assets/images/WireGlasses.png';
export default function TryOnScreen({ navigation }) {
    const [hasPermission, setHasPermission] = React.useState();
    const [faceData, setFaceData] = React.useState([]);
    React.useEffect(() => {
       (async () => {
        const {status} = await Camera.requestCameraPermissionsAsync();
        setHasPermission(status === "granted");
       }) ();
    }, []);
    if (hasPermission === false) {
        return <Text>No access to camera</Text>;
    }
    function getFaceDataView(){
        if (faceData.length === 0){
            return(<View style={styles.faces}>
                <Text style={styles.faceDesc}> No faces D: </Text>
                </View>
            );
        } else {
            return faceData.map((face, index) => {
                const smiling = face.smilingProbability > 0.7;
                const rightEyePosX = face.rightEyePosition.x;
                const rightEyePosY = face.rightEyePosition.y;
                const leftEyePosX = face.leftEyePosition.x;
                const leftEyePosY = face.leftEyePosition.y;
                const faceWidth = face.bounds.size.width;
                const sizeRatio = (faceWidth/50) * 100;
                const faceHeight = face.bounds.size.height - 100;
                const nose = face.noseBasePosition;
                const leftEar = face.leftEarPosition;
                const eyeWidth = faceWidth/4;
                const rollAng = face.rollAngle;
                let headAng = rollAng + "deg";
                console.log (headAng)
                return (
                    <View style={styles.faces} key={index}>
                        <Image id='glasses' style={{
                            justifyContent: 'center',
                            position: 'absolute',
                            width: faceWidth/1.2,
                            left: (leftEyePosX - eyeWidth)-16,
                            top: (leftEyePosY - 650),
                            transform: [{ rotateZ: headAng }],
                            resizeMode: 'contain',
                        }} source={glasses}></Image>
                    </View>
                );
            });
        }
    }
    const handleFacesDetected = ({ faces }) => {
        setFaceData(faces);
        console.log(faces);
    }
    return (
        <Camera 
            type={Camera.Constants.Type.front}
            style={styles.camera}
            onFacesDetected={handleFacesDetected}
            faceDetectorSettings={{
                mode: FaceDetector.FaceDetectorMode.fast,
                detectLandmarks: FaceDetector.FaceDetectorLandmarks.all,
                runClassifications: FaceDetector.FaceDetectorClassifications.all,
                minDetectionInterval: 100,
                tracking: true
            }}>
            {getFaceDataView()}
        </Camera>
    );
}
const styles = StyleSheet.create({
    camera: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
    },
    faces: {
        backgroundColor: '#ffffff',
        alignSelf:'stretch',
        alignItems:'center',
        justifyContent: 'center',
        margin: 16
    },
    faceDesc: {
        fontSize: 20
    }
});