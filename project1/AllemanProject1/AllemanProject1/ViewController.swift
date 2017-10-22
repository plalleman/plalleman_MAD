//
//  ViewController.swift
//  AllemanProject1
//
//  Created by Paige Alleman on 10/22/17.
//  Copyright © 2017 Paige Alleman. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var milesfield: UITextField!
    @IBOutlet weak var numwayscontrol: UISegmentedControl!
    @IBOutlet weak var mpgSlider: UISlider!
    @IBOutlet weak var gasCostField: UITextField!
    @IBOutlet weak var driveCostLabel: UILabel!
    @IBOutlet weak var mpgSliderLabel: UILabel!
    
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    //function to perform the caluclation. Segmented control is covered by an if statement
    @IBAction func calculatecost(_ sender: Any) {
        var miles:Float
        var mpg = mpgSlider.value
        var gasmoney:Float
        var cost:Float
        
        //get value for miles to destination
        if milesfield.text!.isEmpty{
            miles = 0.0
        }
        else{
            miles = Float(milesfield.text!)!
        }
        //get value for cost of gas
        if gasCostField.text!.isEmpty{
            gasmoney = 0.0
        }
        else{
            gasmoney = Float(gasCostField.text!)!
        }
        
        //one way or roundtrip?
        var roundtrip: Bool
        if numwayscontrol.selectedSegmentIndex == 1{
            roundtrip = true
            
        }
        else{
            roundtrip = false
        }
        
        //okay now actually calculate
        if roundtrip == true{
            cost = ((miles*2)/mpg)*gasmoney
        }
        else{
            cost = (miles/mpg)*gasmoney
        }
        
        driveCostLabel.text = String(cost)
        
        
    }
    
    //function to make the mpg label show what the slider value is
    @IBAction func mpgLabelUpdate(_ sender: Any) {
        let mpg = mpgSlider.value
        mpgSliderLabel.text = String(mpg)
    }
    
    override func viewDidLoad() {
        milesfield.delegate=self
        gasCostField.delegate=self
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

