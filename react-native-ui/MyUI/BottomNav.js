import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import * as React from 'react';
import Ionicons from 'react-native-vector-icons/Ionicons';

import CartScreen from './Screens/CartScreen';
import FavScreen from './Screens/FavScreen';
import HomeScreen from './Screens/HomeScreen';
import ItemDetailsScreen from './Screens/ItemDetailsScreen';
import TryOnScreen from './Screens/TryOnScreen';
import ProfileScreen from './Screens/ProfileScreen';


const parentHomeName = 'parentHome';
const homeName = 'Home';
const favName = 'Favs';
const parentCartName = 'parentCart';
const cartName = 'Cart';
const profileName = 'Profile';

const Tab = createBottomTabNavigator();
const HomeStack = createStackNavigator();
const CartStack = createStackNavigator();

const num = '3';


function HomeStackScreen() {
    return (
        <HomeStack.Navigator screenOptions={{
            headerShown: false
        }}>

            <HomeStack.Screen name={homeName}
                component={HomeScreen} />

            <HomeStack.Screen name="ItemDetail"
                component= {ItemDetailsScreen} />
            
            <HomeStack.Screen name="TryOn"
                component= {TryOnScreen} />

        </HomeStack.Navigator>
    );
}

function CartStackScreen(props) {

    // const [isCartFull, setIsCartFull] = React.useState(false);

    return (
        <CartStack.Navigator screenOptions={{
            headerShown: false
        }}>

            <CartStack.Screen 
            name={cartName}
                // component={CartScreen}
                // setIsCartFull={props.setIsCartFull}
                children={()=><CartScreen setIsCartFull={props.setIsCartFull} navigation={props.navigation}/>}
                /> 

            <CartStack.Screen name="ItemDetail"
                component={ItemDetailsScreen} />

            <CartStack.Screen name="TryOn"
                component= {TryOnScreen} />


        </CartStack.Navigator>
    );
}


export default function BottomNav({ navigation }) {
    const [isCartFull, setIsCartFull] = React.useState(false);

    return (
        <NavigationContainer>
            <Tab.Navigator
                initialRouteName={'Home'}

                screenOptions={({ route }) => ({
                    headerShown: false,
                    tabBarStyle: {
                        position: 'absolute',
                        padding: '2.5%',
                        elevation: 0,
                        height: 90,
                        borderTopWidth: .5,
                        borderTopColor: "black",
                        backgroundColor: "#fefaf5"
                    },

                    tabBarIcon: ({ focused, color, size }) => {
                        let iconName;
                        let rn = route.name;

                        if (rn === parentHomeName) {
                            iconName = 'home-outline'
                        } else if (rn === favName) {
                            iconName = 'heart-outline'
                        } else if (rn === profileName) {
                            iconName = 'person-outline'
                        } else if (rn === parentCartName && isCartFull === true) {
                            iconName = 'cart'
                        } else if (rn === parentCartName && isCartFull === false) {
                            iconName = 'cart-outline'
                        }

                        return <Ionicons name={iconName} size={40} color={color} />
                    }
                })}

                tabBarOptions={{
                    activeTintColor: '#D82130',
                    inactiveTintColor: 'black',
                    showLabel: false,
                }}
            >

                <Tab.Screen name={parentHomeName} component={HomeStackScreen} />
                <Tab.Screen name={favName} component={FavScreen} />
                <Tab.Screen name={profileName} component={ProfileScreen} />
                {/* <Tab.Screen name={parentCartName} children={() => <CartStackScreen />} /> */}
                {/* <Tab.Screen name={parentCartName} component={CartStackScreen}/> */}
                <Tab.Screen name={parentCartName} children={()=> <CartStackScreen setIsCartFull={setIsCartFull} navigation={navigation}/>} />

            </Tab.Navigator>
        </NavigationContainer>
    )
}

