import React, { useState } from "react";
import { Alert, Modal, Pressable, StyleSheet, Text, View } from "react-native";
import { TouchableOpacity } from "react-native-gesture-handler";


export default function CartModal(props) {

    const [modalVisible, setModalVisible] = useState(false);

    return (
        <View>
            <Modal
                animationType="slide"
                transparent={true}
                visible={modalVisible}
                onRequestClose={() => {
                    Alert.alert("no mo modal");
                    setModalVisible(!modalVisible);
                }}>

                <View style={styles.bottomView}>
                    <View style={styles.modalView}>
                        <TouchableOpacity
                            style={styles.closeLine}
                            onPress={() => {
                                setModalVisible(!modalVisible)
                            }}
                        >
                        </TouchableOpacity>
                    </View>
                </View>

            </Modal>

            <View style={styles.checkoutOverlay}>
                <Text style={{ fontSize: 30, marginTop: 20, marginLeft: 10, }}>Total: </Text>

                <View style={{ flexDirection: 'column', justifyContent: 'space-around' }}>
                    <Text style={{ fontSize: 30, textAlign: 'right', marginRight: 10, paddingBottom: 10, marginTop: -36 }}>${props.price.toFixed(2)}</Text>

                    <Pressable
                        style={[styles.button, styles.buttonOpen]}
                        onPress={() => setModalVisible(true)}
                    >
                        <Text style={{
                            fontSize: 15,
                            fontFamily: "Gruppo_400Regular",
                        }}>Checkout</Text>
                    </Pressable>

                </View>

            </View>

        </View>
    );
};


const styles = StyleSheet.create({
    bottomView: {
        height: '50%',
        justifyContent: "top",
        alignItems: "center",
        marginTop: 'auto',
        backgroundColor: '#fefaf5',
        borderColor: 'black',
        borderTopRightRadius: 20,
        borderTopLeftRadius: 20,
        borderWidth: 1
    },

    modalView: {
        marginTop: 15
    },

    checkoutOverlay: {
        zIndex: 0,
        marginTop: -30,
        backgroundColor: '#FFFBF6',
        borderRadius: 20,
        borderWidth: 1,
        paddingBottom: 40
    },

    button: {
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: 10,
        borderColor: 'black',
        borderWidth: 1,
        height: 41,
        width: 109,
        marginLeft: 305,
        marginTop: 15
    },

    buttonOpen: {
        backgroundColor: "#fefaf5",
    },

    closeLine: {
        borderColor: 'black',
        borderWidth: 2,
        borderRadius: 20,
        height: 0,
        width: 120
    }
});
