from keras.models import load_model
import tensorflow as tf
import numpy as np
def Prediction(image):
    model = load_model('/home/orlane/Documents/RepairIt/fix_it.h5')
    classes=['cuisiniereFour', 'machineALaver', 'ordinateurs', 'telephone', 'television', 'voiture']
    img = tf.keras.utils.load_img(
        predic, target_size=(180, 180)
    )
    img_array = tf.keras.utils.img_to_array(img)
    img_array = tf.expand_dims(img_array, 0) 
    predictions = model.predict(img_array)
    score = tf.nn.softmax(predictions[0])
    
    print(
        "Classe {} a {:.2f} pourcent ."
        .format(classes[np.argmax(score)], 100 * np.max(score))
    )
Prediction(predic)
