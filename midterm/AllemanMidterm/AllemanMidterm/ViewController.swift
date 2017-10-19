//
//  ViewController.swift
//  AllemanMidterm
//
//  Created by Paige Alleman on 10/19/17.
//  Copyright © 2017 Paige Alleman. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    //make outlets for literally everything
    @IBOutlet weak var commuteMilesField: UITextField!
    @IBOutlet weak var GasTankLabel: UILabel!
    @IBOutlet weak var commuteTimeLabel: UILabel!
    @IBOutlet weak var GalNeededLabel: UILabel!
    @IBOutlet weak var gasSlider: UISlider!
    @IBOutlet weak var vehicleChooser: UISegmentedControl!
    
    @IBOutlet weak var transportImage: UIImageView!
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    
    //segmented control function - change image and commute time
    @IBAction func changeTransMethod(_ sender: UISegmentedControl) {
        var commutemiles: Float
        if commuteMilesField.text!.isEmpty {
            commutemiles = 0.0
        }
        else {
            commutemiles = Float(commuteMilesField.text!)!
        }
        let bikeTime = (commutemiles/10)*60
        let busTime = ((commutemiles/20)*60) + 10
        
        if vehicleChooser.selectedSegmentIndex == 0{
            //choosing to drive a car
            transportImage.image = UIImage(named: "caricon")
            
            //add an alert that carpooling is a good idea
            let alert=UIAlertController(title: "Driving?", message: "Consider carpooling! Make a friend, save on gas!", preferredStyle: UIAlertControllerStyle.alert)
            //no action needed so just make a dismiss button that says OK
            let cancelAction=UIAlertAction(title: "OK", style:UIAlertActionStyle.cancel, handler: nil)
            alert.addAction(cancelAction) //adds the alert action to the alert object
            present(alert, animated: true, completion: nil)
            
        }
        else if vehicleChooser.selectedSegmentIndex == 1{
            //choose to take the bus
            transportImage.image = UIImage(named: "busicon")
            //update gas to purchase and commute time
            GalNeededLabel.text="0.0"
            commuteTimeLabel.text=String(busTime)
        }
        else if vehicleChooser.selectedSegmentIndex == 2{
            //choose to bike
            transportImage.image = UIImage(named: "bikeicon")
            //update gas to purchase and commute time
            GalNeededLabel.text="0.0"
            commuteTimeLabel.text=String(bikeTime)
        }
    }
    //Function to update the gas in tank label
    @IBAction func changeGasLabel(_ sender: Any) {
        //get the value from the slider and update the label
        var gasTankValue = gasSlider.value
        GasTankLabel.text = String(gasTankValue)
    }
    
    //function called when commute button is pressed
    @IBAction func CalculateCommute(_ sender: Any) {
        var commutemiles: Float //store commute miles
        var gasTank = gasSlider.value //store slider value
        
        //make sure something is entered in the text field
        if commuteMilesField.text!.isEmpty {
            commutemiles = 0.0
        } else {
            commutemiles = Float(commuteMilesField.text!)!
        }
        
        //total commute time calculation and label update
        let dailyTime = (commutemiles/20)*60
        commuteTimeLabel.text = String(dailyTime)
        
        //gas needed to purchase calculation & label update
        let GasNeeded = (commutemiles/24)-gasTank
        //if the value is positive then we need to buy gas
        if GasNeeded > 0 {
            GalNeededLabel.text=String(GasNeeded)
        }
        //if the value is negative then we do not need to purchase gas
        else{
            GalNeededLabel.text="0.0"
        }
        
    }
    
    override func viewDidLoad() {
        commuteMilesField.delegate=self
        super.viewDidLoad()
    
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

