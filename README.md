# CryptoCraze
A Mock Cryptocurrency trading app developed with [Jetpack Compose](https://developer.android.com/jetpack/compose)

### Work in progress: 🚧

## Features
### Live Crypto Prices

Live cryptocurrency prices with account balance matching portfolio

<table cellspacing="0" cellpadding="0">
 <tr>
    <td><img src="screenshots/home_screen.png" alt="Live Crypto Prices" width="200"/></td>
   </tr> 
</table>

### Buy/Sell Crypto

Buy and sell cryptocurrency with created fiat wallet or Crypto Craze Visa Card balance

<table cellspacing="0" cellpadding="0">
 <tr>
    <td><img src="screenshots/buy_sell_bottom.png" alt="Buy/Sell" width="200"/></td>
    <td><img src="screenshots/buy_sell_list.png" alt="Buy/Sell" width="200"/></td>
   </tr> 
</table>
<table cellspacing="0" cellpadding="0">
 <tr>
    <td><img src="screenshots/buy_sell_screen.png" alt="Buy/Sell" width="200"/></td>
    <td><img src="screenshots/purchase_successful.png" alt="Buy/Sell" width="200"/></td>
   </tr> 
</table>

### Wallet

Fiat Wallet/Crypto Craze Visa Card payment creation options

<table cellspacing="0" cellpadding="0">
 <tr>
    <td> <img src="screenshots/wallet_screen.png" alt="Wallet" width="200"/></td>
   </tr> 
</table>
<table cellspacing="0" cellpadding="0">
 <tr>
    <td><img src="screenshots/create_visa_card.png" alt="Wallet" width="200"/></td>
    <td><img src="screenshots/create_fiat_wallet_card.png" alt="Wallet" width="200"/></td>
   </tr> 
</table>

### Portfolio

See your currently owned cryptocurrency with your portfolio

<table>
 <tr>
    <td> <img src="screenshots/portfolio_screen.png" alt="Wallet" width="200"/></td>
   </tr> 
</table>

### Transaction History 

<table>
 <tr>
    <td> <img src="screenshots/transaction_history_screen.png" alt="Wallet" width="200"/></td>
   </tr> 
</table>

## Architecture

Clean Architecture
* Presentation
* Domain
* Data

Use Cases

Dependency Injection

### Libraries/tools used

* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Room](https://developer.android.com/training/data-storage/room)
* [Retrofit](https://square.github.io/retrofit/)
* [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
* [Coil](https://github.com/coil-kt/coil)
* [Lottie](https://github.com/airbnb/lottie-android)
* [Gson](https://github.com/google/gson)
* [Alorma Compose Settings](https://github.com/alorma/Compose-Settings)
* [Truth](https://truth.dev/)

## License
```
MIT License

Copyright (c) 2021 BrandonRNeath

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```