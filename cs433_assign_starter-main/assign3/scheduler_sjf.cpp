/**
* Assignment 3: CPU Scheduler
 * @file scheduler_sjf.cpp
 * @author Connor Penn & Victor Mullison (TODO: your name)
 * @brief This Scheduler class implements the SJF scheduling algorithm.
 * @version 0.1
 */
//You must complete the all parts marked as "TODO". Delete "TODO" after you are done.
// Remember to add sufficient and clear comments to your code

#include "scheduler_sjf.h"

// TODO: add implementation of SchedulerSJF constructor, destrcutor and 
// member functions init, print_results, and simulate here
SchedulerSJF::SchedulerSJF() {
    total_time = 0;
    turnaround_time = 0;
    average_turnaround = 0;
    average_wait = 0;
}

SchedulerSJF::~SchedulerSJF() {
    for (int i = 0; i < scheduler_list.size(); i++) {
        delete scheduler_list[i] ;
    }
}

void SchedulerSJF::init(std::vector<PCB>& process_list) {
    for(int i = 0; i < process_list.size(); i++) {
        scheudler_list.push(process_list[i]) ;
    }
}

void SchedulerSJF::print_results() {
    for(int i = 0; i < scheduler_list[i]; i++) {
        std::cout << scheduler_list[i].id << " turn_around time = " << scheduler_list[i].turnaround_time << ", waiting time = " << scheduler_list[i].waiting_time << endl ;
        average_turnaround += scheduler_list[i].turnaround_time ;
        average_wait += scheduler_list[i].waiting_time ;
    }
    average_turnaround = average_turnaround / scheduler_list.size() ;
    average_wait = average_wait / scheduler_list.size() ;
    std::cout << "Average turn-around time = " << average_turnaround << ", Average waiting time = " << average_wait << endl ;
}

void SchedulerSJF::simulate() {
    
    //sorting list for ease of access
    for (int i = 0; i<scheduler_list.size(); i++) {
        swapped = false;
        for(int j = 0; j < scheduler_list.size(); i++) {
            if (scheduler_list[j].burst_time < scheduler_list[i].burst_time) {
                swap(scheduler_list[i], scheduler_list[j]) ;
                swapped = true ;
            }
        }
        if (swapped == false) {
            break ;
        }
    }
    for (int i = 0; i<scheduler_list.size(); i++) {
        scheduler_list[i].waiting_time = total_time ;
        total_time += scheduler_list[i].burst_time ;
        scheduler_list[i].turnaround_time = total_time ;
    }
}