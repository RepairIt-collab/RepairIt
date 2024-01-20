from flask import Flask, request
import numpy as np
import tensorflow as tf
from keras.models import load_model


# Initialize Flask application
app = Flask(__name__)

# Define API endpoint for image classification
@app.route('/predict', methods=['POST'])
def prediction():
    
    image = request.get_data(as_text=True)
    model = load_model('/home/ketsia-kem/Documents/l3/331/projet/REPAIRIT_FINAL/fix_it.h5')
    classes=['cuisiniereFour', 'machineALaver', 'ordinateurs', 'telephone', 'television', 'voiture']
    img = tf.keras.utils.load_img(
        image, target_size=(180, 180)
    )
    img_array = tf.keras.utils.img_to_array(img)
    img_array = tf.expand_dims(img_array, 0) 
    predictions = model.predict(img_array)
    score = tf.nn.softmax(predictions[0])
    predict = classes[np.argmax(score)]
    return predict

# Run the Flask application
if __name__ == '__main__':
    app.run(debug=True)