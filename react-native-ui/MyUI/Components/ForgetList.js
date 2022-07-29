import React from 'react';
import { FlatList, Image, SafeAreaView, StatusBar, StyleSheet, View } from 'react-native';
import ForgotItem from './ForgotItem';


export default function ForgetList(props) {
    return (
        <SafeAreaView style={styles.container}>
            <FlatList
                data={props.cartitems}
                horizontal={true}
                showsHorizontalScrollIndicator={false}
                renderItem={({ item }) => (
                    <View >
                        <ForgotItem item={item} />
                    </View>
                )
                }
                keyExtractor={item => item.id}
            />
        </SafeAreaView>
    );
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        marginTop: StatusBar.currentHeight || 0,
    },

    title: {
        fontSize: 20,
    }
});
