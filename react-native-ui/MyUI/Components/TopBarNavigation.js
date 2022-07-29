import { AntDesign } from "@expo/vector-icons";
import { Pressable, StyleSheet, Text } from "react-native";


export default function TopBarNavigation(props, { navigation }) {
    return (
        <Pressable style={{ flexDirection: 'row', marginBottom: -50, zIndex: 1 }}
            onPress={() => {
                props.navigation.goBack();
            }}>
            <AntDesign name="left" style={styles.backArrow} />
            <Text style={styles.backButtonText}>
                Home</Text>
        </Pressable>
    )
}


const styles = StyleSheet.create({
    backButtonText: {
        fontSize: 36,
        fontFamily: "Gruppo_400Regular",
    },

    backArrow: {
        width: 30,
        height: 30,
        marginTop: 5,
        fontSize: 30,
    },
});