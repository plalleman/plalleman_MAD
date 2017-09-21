//
//  ViewController.swift
//  lab2
//
//  Created by Paige Lorraine Alleman on 9/14/17.
//  Copyright © 2017 Paige Lorraine Alleman. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var colorsImage: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var colorSwitcher: UISegmentedControl!
    
    @IBOutlet weak var capSwitch: UISwitch!
    @IBOutlet weak var colorSlider: UISlider!
    @IBAction func changeInfo(_ sender: UISegmentedControl) {
            if colorSwitcher.selectedSegmentIndex == 0 {
                titleLabel.text="Primary Colors"
                colorsImage.image=UIImage(named: "primary-colors")
            }
            else if colorSwitcher.selectedSegmentIndex == 1{
                titleLabel.text="Secondary Colors"
                colorsImage.image=UIImage(named: "secondary-colors")
            }
    }


    
    @IBAction func changeColor(_ sender: Any) {
        if colorSlider.value==0{
            titleLabel.textColor=UIColor.red
        }
        else if colorSlider.value > 0 && colorSlider.value <= 1{
            titleLabel.textColor=UIColor.orange
        }
        else if colorSlider.value > 1 && colorSlider.value <= 2{
            titleLabel.textColor=UIColor.yellow
            
        }
        else if colorSlider.value > 2 && colorSlider.value <= 3{
            titleLabel.textColor=UIColor.green
        }
        else if colorSlider.value > 3 && colorSlider.value <= 4{
            titleLabel.textColor=UIColor.blue
        }
        else {
            titleLabel.textColor=UIColor.purple
        }
    }
    @IBAction func changeCap(_ sender: UISwitch) {
        if capSwitch.isOn{
            titleLabel.text=titleLabel.text?.uppercased()
        }
        else{
            titleLabel.text=titleLabel.text?.capitalized
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

