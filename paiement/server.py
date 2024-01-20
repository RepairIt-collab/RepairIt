#! /usr/bin/env python3.6
"""
Python 3.6 or newer required.
"""
import json
import os
import stripe

# This is your test secret API key.
stripe.api_key = 'sk_test_51OJ8k3IyLHKyHjYEykfWrDiF988sFG4o1goYnx0Bi7BcOXJM65aOY32FIU9mnFOEIsWuqn4EoIHUoIboSzcH5F9X00zk8cnOtN'

from flask import Flask, render_template, jsonify, request


app = Flask(__name__, static_folder='public',
            static_url_path='', template_folder='public')

def calculate_order_amount(items):
    print(items)
    # Replace this constant with a calculation of the order's amount
    # Calculate the order total on the server to prevent
    # people from directly manipulating the amount on the client
    return 1400


@app.route('/create-payment-intent', methods=['POST'])
def create_payment():
    try:
        data = json.loads(request.data)
        # Create a PaymentIntent with the order amount and currency
        intent = stripe.PaymentIntent.create(
            amount=calculate_order_amount(data['items']),
            currency='usd',
            # In the latest version of the API, specifying the `automatic_payment_methods` parameter is optional because Stripe enables its functionality by default.
            automatic_payment_methods={
                'enabled': True,
            },
        )
        return jsonify({
            'clientSecret': intent['client_secret'],
            'items':data['items']
        })
    except Exception as e:
        return jsonify(error=str(e)), 403

if __name__ == '__main__':
    app.run(port=4242)